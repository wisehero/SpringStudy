package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    // 하나의 회원이 여러 개의 주문 건수를 가지고 있다.
    @OneToMany(mappedBy = "member") // 나는 Order에 있는 member에 연결되었다!
    private List<Order> orders = new ArrayList<>();

}
