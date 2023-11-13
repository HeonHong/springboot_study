package hello.proxy.proxyFactory;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ProxyFactoryTest {

    @Test
    @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
    void interfaceProxy(){

        ServiceInterface target =  new ServiceImpl();
        //여기서 이미 target을 넣어주기 때문에 TimeAdvice에서 Target을 생성자로 받을 필요가 없다.
        //인터페이스가 잇으면 인터페이스를 기반으로 jdk 동적 프록시를 생성한다.
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.save();


        //프록시 적용여부 확인 방법
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
//        assertThat(AopUtils.isAopProxy(proxy)).isFalse();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }

    @Test
    @DisplayName("구체 클래스만 있으면 CGLIB를 사용")
    void concreteProxy(){

        ConcreteService target =  new ConcreteService();
        //여기서 이미 target을 넣어주기 때문에 TimeAdvice에서 Target을 생성자로 받을 필요가 없다.
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.call();


        //프록시 적용여부 확인 방법
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
//        assertThat(AopUtils.isAopProxy(proxy)).isFalse();
//        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
//        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();


    }



    @Test
    @DisplayName("ProxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLIB를사용하고, 클래스 기반 프록시 사용")
    void proxyTargetClass(){

        ServiceInterface target =  new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);

        //!!매우 중요. 무조건 class를 상속받아서 proxy로 사용하는 것이다.
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.save();


        //프록시 적용여부 확인 방법
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
//        assertThat(AopUtils.isAopProxy(proxy)).isFalse();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }
}
