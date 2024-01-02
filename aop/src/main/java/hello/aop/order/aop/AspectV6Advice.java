package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Advice {


    //hello.aop.order 패키지와 하위 패키지 이면서 클래스 이름 패턴이 *Service
//    @Around("hello.aop.order.aop.Pointcuts.orderAndService()")
//    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable{
//        try{
//            //@Before()
//            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
//            Object result = joinPoint.proceed();
//
//            //@AfterReturning
//            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
//            return result;
//        }catch(Exception e){
//            //@AfterThrowing
//            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
//            throw e;
//        }finally {
//
//            //@After
//            log.info("[리소스 릴리즈 커밋] {}", joinPoint.getSignature());
//
//        }
//    }

    @Before("hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint){
        //이전 처럼 joinPoint.Proceed() 사용 불가
        //하지만 여기까지 만들어 놓으면 알아서 메서드를 실행
        log.info("[before] {}" , joinPoint.getSignature());
    }

    @AfterReturning(value = "hello.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result){
        //@Aroung와 같이 result 객체를 변경할 수는 없다.
        //대신 setter사용이 가능한 경우 값을 조작은 할 수 있다.
        log.info("[return] {} result={}" , joinPoint.getSignature(), result);
    }

    @AfterReturning(value = "hello.aop.order.aop.Pointcuts.allOrder()", returning = "result")
    public void doReturn2(JoinPoint joinPoint, String result){
        //반환값의 type오류가 있을 경우에는 호출 되지 않는다
        //ex.반환 type이 Integer인 경우
        log.info("[return2] {} result={}" , joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "hello.aop.order.aop.Pointcuts.orderAndService()", throwing ="ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex){
        //자동을 Throw e됨
        log.info("[ex] {} message={}" , joinPoint.getSignature(), ex.getMessage());
    }

    @After(value = "hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doAFter(JoinPoint joinPoint){
        //메서드 실행이 종료되면 실행
        //정상 및 예외 반환 조건을 모두 처리
        //일반적으로 리소스를 해제하는데 사용
        log.info("[after] {}" , joinPoint.getSignature());
    }
}
