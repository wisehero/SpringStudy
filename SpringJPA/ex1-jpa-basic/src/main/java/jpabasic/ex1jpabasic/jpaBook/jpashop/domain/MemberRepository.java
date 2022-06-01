package jpabasic.ex1jpabasic.jpaBook.jpashop.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m left join m.team")
    List<Member> findMemberJoin();

    @Query("select m from Member m left join fetch  m.team")
    List<Member> findMemberFetchJoin();



}