package study.datajpa.repository;

import org.springframework.beans.factory.annotation.Value;

public interface UserNameOnly {
//    String getUsername();

    @Value("#{target.username + ' ' + target.age + ' ' + target.team.name")
        // 스프링 EL 문법 지원. 단, SELECT절 최적화가 안된다.
    String getUsername();
}
