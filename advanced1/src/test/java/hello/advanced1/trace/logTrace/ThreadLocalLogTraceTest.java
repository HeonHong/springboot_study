package hello.advanced1.trace.logTrace;

import hello.advanced1.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class ThreadLocalLogTraceTest {

    ThreadLocalLogTrace trace = new ThreadLocalLogTrace();

    @Test
    void begin_end_level3(){
        TraceStatus status1 = trace.begin("Hello");
        TraceStatus status2 = trace.begin("Hello");
        TraceStatus status3 = trace.begin("Hello");
        trace.end(status3);
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception_level3(){
        TraceStatus status1 = trace.begin("Hello");
        TraceStatus status2 = trace.begin("Hello");
        TraceStatus status3 = trace.begin("Hello");
        trace.exception(status3,new IllegalStateException());
        trace.exception(status2,new IllegalStateException());
        trace.exception(status1,new IllegalStateException());
    }
}