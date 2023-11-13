package hello.proxy.advisor;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class AdvisorTest {
    //advisor : proxyFactory사용시 필수!!!!
    //이전에 ProxyFactoryTest에서 advice만 넣고도 가능했던 이유는
    //advice내부를 보면 new DefaultPointcutAdvisor(Pointcut.TRUE,) 설정이 있어서이다.
    //advice : 부가기능 역할
    //pointcut : 필터링 역할
    @Test
    void advisorTest1(){
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        //일단은 무조건 pointcut을 true로 반환하게 설정
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());
        proxyFactory.addAdvisor(advisor);
        ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();

        proxy.save();
        proxy.find();
    }
}
