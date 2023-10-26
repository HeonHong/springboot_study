package hello.advanced1.v0.trace.helloTrace;

import hello.advanced1.trace.TraceStatus;
import hello.advanced1.trace.helloTrace.HelloTraceV1;
import hello.advanced1.trace.helloTrace.HelloTraceV2;
import org.junit.jupiter.api.Test;

public class HelloTraceV2Test {

    @Test
    void begin_end(){
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status = trace.begin("Hello");
        TraceStatus status2 = trace.beginSync(status.getTraceId(),"Hello2");
        TraceStatus status3 = trace.beginSync(status2.getTraceId(),"Hello3");
        TraceStatus status4 = trace.beginSync(status3.getTraceId(),"Hello4");
        trace.end(status4);
        trace.end(status3);
        trace.end(status2);
        trace.end(status);

    }


    @Test
    void begin_exception(){
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status = trace.begin("exception");
        TraceStatus status2 = trace.beginSync(status.getTraceId(),"exception2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status, new IllegalStateException());
    }
}
