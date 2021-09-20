package com.example.springshop.repository;

import com.example.springshop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {

    List<Item> findByItemName(String itemName);

    // Or 조건 처리 메서드
    List<Item> findByItemNameOrItemDetail(String itemName, String itemDetail);

    // LessThan
    List<Item> findByPriceLessThan(Integer price);

    //LessThan & OrderBy
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);


}
