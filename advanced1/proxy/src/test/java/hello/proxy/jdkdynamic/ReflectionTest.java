package hello.proxy.jdkdynamic;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

@Slf4j
public class ReflectionTest {
    @Test
    void reflection0(){
        Hello target = new Hello();

        //공통로직1 시작
        log.info("start");
        String result1 = target.callA(); //호출 메소드가 다름. 리플랙션으로 동적처리 필요.
        log.info("result1={}", result1);
        //공통로직1 종료

        //공통로직2 시작
        log.info("start");
        String result2 = target.callB(); //호출 메소드가 다름
        log.info("result2={}", result2);
        //공통로직1 종료
    }

    @Slf4j
    static class Hello{
        public String callA(){

            log.info("callA");
            return "A";
        }
        public String callB(){

            log.info("callB");
            return "B";
        }
    }


    @Test
    void reflection1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Hello target = new Hello();

        //클래스 메타 정보 가져오기. $는 내부에 존재하는 클래스일 경우
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        //callA 메소드 메타 정보. 실제 인스턴스에 존재하는 메소드를 불러오는 것이 아니라 정보만 불러온다.
        Method methodCallA = classHello.getMethod("callA");
        //target인스턴스에 있는 callA 호출
        Object result1 = methodCallA.invoke(target);
        log.info("result1={}", result1);

        //callB 메소드 정보
        Method methodCallB = classHello.getMethod("callB");
        //target인스턴스에 있는 callA 호출
        Object result2 = methodCallB.invoke(target);
        log.info("result2={}", result2);

    }

    @Test
    void reflection2() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //런타임에 발생하기 때문에 조심해야한다.
        Hello target = new Hello();

        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Method methodCall = classHello.getMethod("callA");
        dynamicCall(methodCall,target);

        methodCall = classHello.getMethod("callB");
        dynamicCall(methodCall,target);

    }

    private void dynamicCall(Method method, Object target) throws InvocationTargetException, IllegalAccessException {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}", result);
    }
}
