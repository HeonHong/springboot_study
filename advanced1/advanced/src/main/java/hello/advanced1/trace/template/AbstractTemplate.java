package hello.advanced1.trace.template;

import hello.advanced1.trace.TraceStatus;
import hello.advanced1.trace.logTrace.LogTrace;

public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    public AbstractTemplate(LogTrace trace){
        this.trace=trace;
    }

    public T execute(String message){
        TraceStatus status = null;
        try {
           status =  trace.begin(message);
           //로직 호출
            T result = call();
            trace.end(status);

            return result;
        }catch (Exception e){
            trace.exception(status, e);
            throw e;//예외 꼭 다시 던저주어야 함.
        }
    }

    protected abstract T call();
}
