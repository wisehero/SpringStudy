package jpabasic.ex1jpabasic;

import jpabasic.ex1jpabasic.jpaBook.jpashop.domain.Member;
import jpabasic.ex1jpabasic.jpaBook.jpashop.domain.MemberRepository;
import jpabasic.ex1jpabasic.jpaBook.jpashop.domain.Team;
import jpabasic.ex1jpabasic.jpaBook.jpashop.domain.TeamRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
@Transactional
public class fetchJoinTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @PersistenceContext
    EntityManager em;


    @Test
    @DisplayName("일반 조인")
    void findMember() {
        Team teamA = new Team("TeamA");
        Team teamB = new Team("TeamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member member1 = new Member("member1", teamA);
        Member member2 = new Member("member2", teamA);
        Member member3 = new Member("member3", teamB);
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        em.flush();
        em.clear();

        List<Member> findMember = memberRepository.findMemberJoin();
        findMember.forEach(m -> System.out.println("member:" + m.toString()));
        System.out.println("========================================");
        findMember.forEach(m -> System.out.println(m.getTeam().getName()));
    }

    @Test
    @DisplayName("페치 조인")
    void findMemberFetch() {
        Team teamA = new Team("TeamA");
        Team teamB = new Team("TeamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member member1 = new Member("member1", teamA);
        Member member2 = new Member("member2", teamA);
        Member member3 = new Member("member3", teamB);
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        em.flush();
        em.clear();

        List<Member> findMember = memberRepository.findMemberFetchJoin();

        findMember.forEach(m -> System.out.println("member:" + m.toString() + "," + m.getTeam().getName()));
        System.out.println("==========================================================");
        findMember.forEach(m -> System.out.println(m.getTeam().getName()));
    }

    @Test
    @DisplayName("컬렉션 페치 조인")
    void collectionFetch() {
        Team teamA = new Team("TeamA");
        Team teamB = new Team("TeamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member member1 = new Member("member1", teamA);
        Member member2 = new Member("member2", teamA);
        Member member3 = new Member("member3", teamB);
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        em.flush();
        em.clear();

        List<Team> findTeams = teamRepository.findTeamByFetch();

        for (Team team : findTeams) {
            System.out.println("teamname = " + team.getName() + " team = " + team);

            for (Member member : team.getMembers()) {
                System.out.println("-> username = " + member.getUsername() + ", member = " + member);
            }
        }
    }
}