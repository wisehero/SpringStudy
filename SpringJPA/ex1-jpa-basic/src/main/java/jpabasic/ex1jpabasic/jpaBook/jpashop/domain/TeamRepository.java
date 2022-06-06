package jpabasic.ex1jpabasic.jpaBook.jpashop.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("SELECT t FROM Team t join fetch t.members where t.name='TeamA'")
    List<Team> findTeamByFetch();

    @Query("SELECT DISTINCT t FROM Team t join fetch t.members where t.name='TeamA'")
    List<Team> findTeamByFetchDistinct();
}