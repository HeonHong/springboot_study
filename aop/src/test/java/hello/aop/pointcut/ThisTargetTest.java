package hello.aop.pointcut;

import hello.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(ThisTargetTest.ThisTargetAspect.class)
//application.propties에서 cglib혹은 jdk동적프록시 적용여부 설정 가능
//혹은 아래와 같이 @SpringBootTest에 properties를 달아주면 됨
//@SpringBootTest(properties = "spring.aop.proxy-target-class=false")//JDK동적프록시
@SpringBootTest(properties = "spring.aop.proxy-target-class=true")//CGLIB

public class ThisTargetTest {

    @Autowired
    MemberService memberService;

    @Test
    void Success(){
        log.info("memberService Proxy={}", memberService.getClass());
        memberService.hello("helloA");

    }

    @Slf4j
    @Aspect
    static  class ThisTargetAspect{

        //부모타입 허용
        @Around("this(hello.aop.member.MemberService)")
        public Object doThisInterFace(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-interface] {}", joinPoint.getSignature());

            return joinPoint.proceed();
        }

        //부모타입 허용
        @Around("target(hello.aop.member.MemberService)")
        public Object doTargetInterFace(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-interface] {}", joinPoint.getSignature());

            return joinPoint.proceed();
        }

        //부모타입 허용
        @Around("this(hello.aop.member.MemberServiceImpl)")
        public Object doThis(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-impl] {}", joinPoint.getSignature());

            return joinPoint.proceed();
        }

        //부모타입 허용
        @Around("target(hello.aop.member.MemberServiceImpl)")
        public Object doTarget(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-impl] {}", joinPoint.getSignature());

            return joinPoint.proceed();
        }

    }
}
