package study.datajpa.entity;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long Id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this(username, 0);
    }

    public Member(String username, int age) {
        this(username, age, null);
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}

/**
 * @Setter : 실무에서 가급적 사용 X
 * @NoArgsContructor AccessLevel.Protected: 기본 생성자 막고 싶은데, JPA 스펙상 Protected로 열어두어야 함
 * @ToString 은 가급적 내부 필드만(연관관계 없는 필드만)
 * ChangeTeam() 으로 양방향 영관관계 한번에 처리
 */
