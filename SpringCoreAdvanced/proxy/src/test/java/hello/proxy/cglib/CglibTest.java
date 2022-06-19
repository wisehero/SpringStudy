package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer(); // CGLIB는 Enhancer를 사용해서 프록시를 생성
        enhancer.setSuperclass(ConcreteService.class); // CGLIB는 구체 클래스를 상속받아서 프록시를 생성할 수 있다.
        enhancer.setCallback(new TimeMethodInterceptor(target)); // 프록시에 적용할 실행 로직을 할당한다.
        ConcreteService proxy = (ConcreteService) enhancer.create(); // 프록시를 생성한다.
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.call();
    }
}
