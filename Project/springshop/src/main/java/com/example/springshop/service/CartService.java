package com.example.springshop.service;

import com.example.springshop.dto.CartItemDto;
import com.example.springshop.entity.Cart;
import com.example.springshop.entity.CartItem;
import com.example.springshop.entity.Item;
import com.example.springshop.entity.Member;
import com.example.springshop.repository.CartItemRepository;
import com.example.springshop.repository.CartRepository;
import com.example.springshop.repository.ItemRepository;
import com.example.springshop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final CartItemRepository cartItemRepository;

    public Long addCart(CartItemDto cartItemDto, String email) {
        Item item = itemRepository.findById(cartItemDto.getItemId()).orElseThrow(EntityNotFoundException::new); // 장바구니에 담을 상품 엔티티를 조회
        Member member = memberRepository.findByEmail(email); //현재 로그인한 회원 이메일을 조회

        Cart cart = cartRepository.findByMemberId(member.getId()); // 상품을 처음으로 장바구니에 담을 경우 해당 회원의 장바구니 엔티티를 생성
        if (cart == null) {
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId()); // 현재 상품이 장바구니에 이미 들어가 있는지 조회

        if (savedCartItem != null) {
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getId();
        } else {
            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }

    }
}
