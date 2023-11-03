package hello.proxy.jdkdynamic.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
public class TimeInvocationHandler implements InvocationHandler {

    //프록시 호출 대상
    private final Object target;

    //jdk가 실행할 로직
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //1. 위의 메소드는 proxy에서 어떤 메소드가 사용되는지 넘어온다.
        //2. args는 invoke될 메소드들 중에서 args를 받아야하는 경우가 있다면 넘겨줘야하기 때문에 존재

        if(args!=null) System.out.println("args확인하기 : " + args[0]);


        log.info("TimeProxy 실행");
        long startMillis = System.currentTimeMillis();

        Object result = method.invoke(target, args);

        long endMillis = System.currentTimeMillis();
        long resultTime = endMillis - startMillis;

        log.info("TimeProxy 종료 resultTime={}", resultTime);
        return result;
    }
}
