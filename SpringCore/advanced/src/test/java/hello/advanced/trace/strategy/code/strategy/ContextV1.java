package hello.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV1 {

    private final Strategy strategy; // 변하는 알고리즘

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    // 변하지 않는 실행 부분
    public void execute() {
        long startTime = System.currentTimeMillis();
        strategy.call(); // 위임
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}

