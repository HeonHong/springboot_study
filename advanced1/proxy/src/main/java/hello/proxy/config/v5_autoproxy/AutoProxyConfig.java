package hello.proxy.config.v5_autoproxy;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AutoProxyConfig {

//    @Bean
    public Advisor getAdvisor1(LogTrace logTrace){
        //pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*","order*","save*");

        //advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }

    //AspectJExpressionPointcut
//    @Bean
    public Advisor getAdvisor2(LogTrace logTrace){
        //pointcut
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        //첫 ..은 app 하위 모든 패키지/ *은 모든 메서드/뒤 ..은 파라미터에 상관x
        //아래 위치에 있어야 poincut 적용됨
        pointcut.setExpression("execution(* hello.proxy.app..*(..))");

        //advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }

    //AspectJExpressionPointcut
    @Bean
    public Advisor getAdvisor3(LogTrace logTrace){
        //pointcut
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        //첫 ..은 app 하위 모든 패키지/ *은 모든 메서드/뒤 ..은 파라미터에 상관x
        //아래 위치에 있어야 poincut 적용됨
        pointcut.setExpression("execution(* hello.proxy.app..*(..)) && !execution(* hello.proxy.app..noLog(..))");

        //advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
