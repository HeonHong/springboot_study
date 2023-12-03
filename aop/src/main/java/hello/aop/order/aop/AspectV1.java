package hello.aop.order.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class AspectV1 {
    //참고.
    // 스프링은 프록시 방식의 aop를 사용하므로 프록시를 통하는 메서드만 적용 대항이 된다.
    // 스프링 aop는 AspectJ의 문법을 차용할 뿐, 프록시 방식의 aop를 제공한다. AspectJ를 직접 사용X
    //@Around같은 annotation도 보면 aspectjweaver 라이브라러리 걸로 나오는데 그냥 인터페이스만 사용함
    //컴파일, 로드타임 위버 등을 사용하지는 않는다.
    @Around("execution(* hello.aop.order..*(..))")//포인트컷
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable{//어드바이스
        log.info("[log] {}", joinPoint.getSignature());//join point 시그니처. 어떤 메소드가 호출되었는지 남김
        return joinPoint.proceed();
    }

}
