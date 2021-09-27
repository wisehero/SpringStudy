package com.example.springshop.service;

import com.example.springshop.constant.ItemSellStatus;
import com.example.springshop.dto.CartItemDto;
import com.example.springshop.entity.Cart;
import com.example.springshop.entity.CartItem;
import com.example.springshop.entity.Item;
import com.example.springshop.entity.Member;
import com.example.springshop.repository.CartItemRepository;
import com.example.springshop.repository.CartRepository;
import com.example.springshop.repository.ItemRepository;
import com.example.springshop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class CartServiceTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CartService cartService;

    @Autowired
    CartItemRepository cartItemRepository;

    public Item saveItem() {
        Item item = new Item();
        item.setItemName("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        return itemRepository.save(item);
    }

    public Member saveMember() {
        Member member = new Member();
        member.setEmail("test@test.com");
        return memberRepository.save(member);
    }


    @Test
    @DisplayName("장바구니 담기 테스트")
    void addCart() {
        Item item = saveItem();
        Member member = saveMember();

        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setCount(5);
        cartItemDto.setItemId(item.getId());

        Long cartItemId = cartService.addCart(cartItemDto, member.getEmail());

        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(EntityNotFoundException::new);

        Assertions.assertThat(item.getId()).isEqualTo(cartItem.getItem().getId());
        Assertions.assertThat(cartItemDto.getCount()).isEqualTo(cartItem.getCount());

    }

}
