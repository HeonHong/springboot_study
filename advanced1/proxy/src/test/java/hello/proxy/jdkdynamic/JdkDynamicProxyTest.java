package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA(){
        AInterface target = new AImpl();

        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        //JDK 차원의 프록시 생성 기술
        //Object proxy=Proxy.newProxyInstance(AInterface.class.getClassLoader(),new Class[]{AInterface.class},handler);
        //구현할 interface가 여러개 일 수 있을 수 있으므로 배열로 만들어준다.
        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(),new Class[]{AInterface.class},handler);

        proxy.call();
        proxy.call2();
        proxy.call3("아규먼트");

        log.info("target = {}", target.getClass());
        //proxy는 Ainterface를 구현받아서 생성
        log.info("proxy = {}", proxy.getClass());
    }
    @Test
    void dynamicB(){
        BInterface target = new BImpl();

        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        //JDK 차원의 프록시 생성 기술
        BInterface proxy= (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(),new Class[]{BInterface.class},handler);

        proxy.call();
        log.info("target = {}", target.getClass());
        //proxy는 Ainterface를 구현받아서 생성
        log.info("proxy = {}", proxy.getClass());
    }
}
