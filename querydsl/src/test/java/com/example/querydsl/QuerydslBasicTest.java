package com.example.querydsl;

import static com.example.querydsl.entity.QMember.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.querydsl.entity.Member;
import com.example.querydsl.entity.Team;
import com.querydsl.jpa.impl.JPAQueryFactory;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {

	@PersistenceContext
	EntityManager em;

	JPAQueryFactory queryFactory;

	@BeforeEach
	public void before() {
		queryFactory = new JPAQueryFactory(em);
		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		em.persist(teamA);
		em.persist(teamB);
		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member2", 20, teamA);
		Member member3 = new Member("member3", 30, teamB);
		Member member4 = new Member("member4", 40, teamB);
		em.persist(member1);
		em.persist(member2);
		em.persist(member3);
		em.persist(member4);
	}

	@Test
	public void startJPQL() {
		String qlString =
				"select m from Member m " +
						"where m.username = :username";
		Member findMember = em.createQuery(qlString, Member.class)
				.setParameter("username", "member1")
				.getSingleResult();

		assertThat(findMember.getUsername()).isEqualTo("member1");
	}

	@Test
	public void startQuerydsl() {
		Member findMember = queryFactory
				.select(member)
				.from(member)
				.where(member.username.eq("member1"))
				.fetchOne();

		assertThat(findMember.getUsername()).isEqualTo("member1");
	}

	@Test
	public void search() {
		Member findMember = queryFactory
				.selectFrom(member)
				.where(member.username.eq("member1")
						.and(member.age.eq(10)))
				.fetchOne();

		assertThat(findMember.getUsername()).isEqualTo("member1");
	}

	@Test
	public void searchAndParam() {
		List<Member> result1 = queryFactory
				.selectFrom(member)
				.where(member.username.eq("member1"),
						member.age.eq(10))
				.fetch();

		assertThat(result1.size()).isEqualTo(1);
	}

	/**
	 *  fetch() -> 리스트 조회, 데이터 없으면 빈 리스트 반환
	 *  fetchOne() -> 단건 조회
	 *  fetchFirst() -> limit(1).fetchOne()
	 */

}
