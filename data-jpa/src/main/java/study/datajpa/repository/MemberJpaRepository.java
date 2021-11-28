package study.datajpa.repository;

import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


/**
 * 순수 JPA 기반 레포지토리 만들기
 * 스프링 데이터 JPA 공통 인터페이스 소개
 * 스프링 데이터 JPA 공통 인터페이스 활용
 * <p>
 * 기본 CRUD
 * 저장
 * 변경 -> 더티체킹
 * 삭제
 * 전체조회
 * 단건조회
 * 카운트
 */
@Repository
public class MemberJpaRepository {

    @PersistenceContext
    EntityManager em;

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public void delete(Member member) {
        em.remove(member);
    }

    public List<Member> findAll() {
        return em.createQuery("select m From Member m", Member.class)
                .getResultList();
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public long count() {
        return em.createQuery("select count(m) from Member m", Long.class)
                .getSingleResult();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
