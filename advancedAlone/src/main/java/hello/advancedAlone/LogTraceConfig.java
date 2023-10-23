package hello.advancedAlone;

import hello.advancedAlone.trace.helloTrace.HelloTrace1;
import hello.advancedAlone.trace.logTrace.LogTrace;
import hello.advancedAlone.trace.logTrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

//    @Bean
//    public LogTrace logTrace(){
//        return new HelloTrace1();
//    }

    @Bean
    public LogTrace logTrace(){
        return new ThreadLocalLogTrace();
    }
}
