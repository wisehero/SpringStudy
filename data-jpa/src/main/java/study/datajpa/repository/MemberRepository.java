package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 실행 시점에
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    // DTO로 직접 조회
    @Query("select new study.datajpa.dto.MemberDto(m.Id, m.username, t.name)"
            + "from Member m join m.team t")
    List<MemberDto> findMemberDto();

    // 파라미터 바인딩
    @Query("select m from Member m where m.username = :name")
    Member findMembers(@Param("name") String username);

    // 컬렉션 파라미터 바인딩
    @Query("select  m from Member m where m.username in : names")
    List<Member> findByNames(@Param("names") List<String> names);


    // 다양한 반환 타입 지원
    List<Member> findByListByUsername(String username); // 컬렉션

    Member findByMemberByUsername(String useranme); /// 단건

    Optional<Member> findOptionalByUsername(String useranme); // 단건 옵셔널
}
