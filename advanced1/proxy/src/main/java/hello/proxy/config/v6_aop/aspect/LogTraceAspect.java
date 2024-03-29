package hello.proxy.config.v6_aop.aspect;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Method;

@Slf4j
@Aspect
//AnnotationAwareAspectJAutoProxyCreator
public class LogTraceAspect {
//1. @AspectJ를 보고 Advisor로 변환해서 저장
//2. 어드바이저를 기반으로 프록시를 생성
    private final LogTrace logTrace;

    public LogTraceAspect(LogTrace logTrace){
        this.logTrace=logTrace;
    }

    @Around("execution(* hello.proxy.app..*(..))")//포인트컷
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{ //어드바이스 로직
        //Advice로직
        TraceStatus status=null;

        try {
            String message = joinPoint.getSignature().toShortString();
//            Method method = invocation.getMethod();
//            String message = method.getDeclaringClass().getSimpleName() + "."+method.getName()+"*" + "()";
            status =  logTrace.begin(message);

            //target호출
            Object result = joinPoint.proceed();
            logTrace.end(status);
            return result;
        }catch (Exception e){
            logTrace.exception(status,e);
            throw e;
        }
    }
}

