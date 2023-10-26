package hello.advanced1;

import hello.advanced1.trace.logTrace.FieldLogTrace;
import hello.advanced1.trace.logTrace.LogTrace;
import hello.advanced1.trace.logTrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

//    @Bean
//    public LogTrace logTrace(){
//        return new FieldLogTrace();
//    }

    @Bean
    public LogTrace logTrace(){
        return new ThreadLocalLogTrace();
    }
}
