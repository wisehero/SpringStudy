package com.example.springshop.repository;

import com.example.springshop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByMemberId(Long memberId); // 멤버 id로 해당 멤버의 카트를 조회해서 반환
}
