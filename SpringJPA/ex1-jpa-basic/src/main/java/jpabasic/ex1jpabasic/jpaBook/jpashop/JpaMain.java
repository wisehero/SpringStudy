package jpabasic.ex1jpabasic.jpaBook.jpashop;

import jpabasic.ex1jpabasic.jpaBook.jpashop.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        //  트랜잭션 획득
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            logic(em);

            tx.commit();
        } catch (Exception e) {
            tx.rollback(); // 트랜잭션 롤백
        } finally {
            em.close();
        }
        emf.close();
    }

    public static void logic(EntityManager em) {

        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("지한");
        member.setAge(2);

        // 등록
        em.persist(member);

        // 수정
        member.setAge(20);

        // 한 건 조회
        Member findmember = em.find(Member.class, id);
        System.out.println("findMember = " + findmember.getUsername() + ", age = " + findmember.getAge());

        // 목록 조회
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size = " + members.size());

        // 삭제
        em.remove(member);
    }
}
