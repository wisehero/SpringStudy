package board.boardDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BoardDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardDemoApplication.class, args);
    }

}
