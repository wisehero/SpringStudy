package hello.jdkdynamic;

import hello.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        AInterface target = new AImpl(); // 프록시 적용 대상 객체 생성
        TimeInvocationHandler handler = new TimeInvocationHandler(target); // 동적 프록시에 적용할 핸들러 로직
        AInterface proxy = (AInterface) // 프록시 생성
                Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]
                        {AInterface.class}, handler);
        proxy.call();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }

    @Test
    void dynamicB() {
        BInterface target = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        BInterface proxy = (BInterface)
                Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]
                        {BInterface.class}, handler);
        proxy.call();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

    }
}
