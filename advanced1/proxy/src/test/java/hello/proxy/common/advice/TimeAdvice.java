package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


//cglib의 methodInterceptor와 다른 패키지
@Slf4j
public class TimeAdvice implements MethodInterceptor {

//advice는 InvocationHandler와 MethodInterceptor를 추상화한 개념이다.
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeProxy 실행");
        long startMillis = System.currentTimeMillis();

//        Object result = method.invoke(target, args);
        //알아서 target을 찾아서 method에 맞게 인자를 넣고 실행시킨다.
        //굳이 invocation에서 method를 찾아서 진행할 필요x
        Object result = invocation.proceed();

        long endMillis = System.currentTimeMillis();
        long resultTime = endMillis - startMillis;

        log.info("TimeProxy 종료 resultTime={}", resultTime);
        return result;
    }
}
