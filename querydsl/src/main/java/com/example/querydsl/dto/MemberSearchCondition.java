package com.example.querydsl.dto;

import lombok.Data;

// 회원 검색조건 
@Data
public class MemberSearchCondition {
	private String username;
	private String teamName;
	private Integer ageGoe;
	private Integer ageLoe;
}
