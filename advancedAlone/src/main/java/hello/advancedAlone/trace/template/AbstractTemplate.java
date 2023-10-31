package hello.advancedAlone.trace.template;

import hello.advancedAlone.trace.TraceStatus;
import hello.advancedAlone.trace.logTrace.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    public T execute(String message){
        TraceStatus status = null;

        try{
            status = trace.begin(message);
            T result = call();
            trace.end(status);
            return result;
        }catch(Exception e){
            trace.exception(status, e);
            throw e;
        }
    }

    public abstract T call();


}
