package hello.proxy.cglib.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object target;

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {


        log.info("TimeProxy 실행");
        long startMillis = System.currentTimeMillis();

        //method.invoke보다 methodProxy를 내부적으로 권장함. 깊게 알필요 ㄴㄴ
        //Object result = method.invoke(target, args);
        Object result= methodProxy.invoke(target,args);

        long endMillis = System.currentTimeMillis();
        long resultTime = endMillis - startMillis;

        log.info("TimeProxy 종료 resultTime={}", resultTime);
        return result;
    }
}
