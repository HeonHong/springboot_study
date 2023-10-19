package hello.advancedAlone.trace.logTrace;

import hello.advancedAlone.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);

}
