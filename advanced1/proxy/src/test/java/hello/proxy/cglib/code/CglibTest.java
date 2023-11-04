package hello.proxy.cglib.code;

import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    void cglib(){
        ConcreteService target = new ConcreteService();

        //Cglib 만드는 코드
        Enhancer enhancer = new Enhancer();
        //ConcreteService를 상속받아서 동적으로 프록시를 생성한다.
        enhancer.setSuperclass(ConcreteService.class);

        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreteService proxy = (ConcreteService) enhancer.create();
        log.info("targetClass={}",target.getClass());
        log.info("proxyClass={}",proxy.getClass());

        proxy.call();
    }
}
