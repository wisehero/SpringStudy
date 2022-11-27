package com.example.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

// 조회 최적화용 DTO
@Data
public class MemberTeamDto {
	private Long memberId;
	private String username;
	private int age;
	private Long teamId;
	private String teamName;

	// 이 어노테이션을 쓰면 DTO가 QueryDSL에 강하게 의존
	@QueryProjection
	public MemberTeamDto(Long memberId, String username, int age, Long teamId, String teamName) {
		this.memberId = memberId;
		this.username = username;
		this.age = age;
		this.teamId = teamId;
		this.teamName = teamName;
	}
}
