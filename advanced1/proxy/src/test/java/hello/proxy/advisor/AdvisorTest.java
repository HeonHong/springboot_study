package hello.proxy.advisor;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import java.lang.reflect.Method;

@Slf4j
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

    //포인트컷 내부에 classfilter/methodfilter 존재
    //둘 다 true로 반환해야 어드바이스 적용 가능
    @Test
    @DisplayName("직접 만든 포인트컷")
    void advisorTest2(){
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        //일단은 무조건 pointcut을 true로 반환하게 설정
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new MyPointcut(), new TimeAdvice());
        proxyFactory.addAdvisor(advisor);
        ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();

        proxy.save();
        proxy.find();
    }

    static class MyPointcut implements Pointcut{

        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;//무조건 true반환
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return new MyMethodMatcher();
        }
    }

    static class MyMethodMatcher implements MethodMatcher{

        private String matchName="save";;
        @Override
        public boolean matches(Method method, Class<?> targetClass) {

            boolean result = method.getName().equals(matchName);
            log.info("포인트컷 호출 method={} targetClass={}", method.getName(), targetClass);
            log.info("포인트컷 결과 result={}", result);
            return result;
        }

        //아래 두 개는 무시
        @Override
        public boolean isRuntime() {
            return false;//정적인 정보만 사용하여 캐싱
            //true이면 매개변수가 동적으로 변경됨
        }

        @Override
        public boolean matches(Method method, Class<?> targetClass, Object... args) {
            return false;
        }

        //쉬운 예시이고 보통은 AspectJExpressionPointcut
        @Test
        @DisplayName("스프링이 제공하는 포인트컷")
        void advisorTest2(){
            ServiceInterface target = new ServiceImpl();
            ProxyFactory proxyFactory = new ProxyFactory(target);
            NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
            pointcut.setMappedNames("save");
            DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new TimeAdvice());
            proxyFactory.addAdvisor(advisor);
            ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();

            proxy.save();
            proxy.find();
        }

    }

}
