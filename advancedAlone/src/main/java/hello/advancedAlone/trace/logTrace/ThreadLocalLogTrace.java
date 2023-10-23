package hello.advancedAlone.trace.logTrace;

import hello.advancedAlone.trace.TraceId;
import hello.advancedAlone.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalLogTrace implements LogTrace{

    private static final String START_PREFIX="-->";
    private static final String COMPLETE_PREFIX="<--";
    private static final String EX_PREFIX="<X-";

    private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();

    public TraceStatus begin(String message){
       TraceId traceId = traceIdHolder.get();
        if(traceId==null){
            traceIdHolder.set(new TraceId());
        }else{
            traceIdHolder.set(traceId.createNextId());
        }
        Long startMillis = System.currentTimeMillis();
        log.info("[{}] {}{}",traceIdHolder.get().getId(), addSpace(START_PREFIX,traceIdHolder.get().getLevel()),message);
        return new TraceStatus(traceIdHolder.get(),startMillis,message);
    }



    public void end(TraceStatus status){
        complete(status, null);
    }

    public void exception(TraceStatus status, Exception e){
        complete(status, e);
    }



    public void complete(TraceStatus status, Exception e){

        Long endMillis = System.currentTimeMillis();
        Long time = endMillis - status.getStartMillis();

        TraceId traceId = status.getTraceId();
        if(e==null){
            log.info("[{}] {}{} {}ms",traceId.getId(), addSpace(COMPLETE_PREFIX,traceId.getLevel()), status.getMessage(), time);
        }else{
            log.info("[{}] {}{} {}ms ex={}",traceId.getId(), addSpace(EX_PREFIX,traceId.getLevel()),status.getMessage(), time, e.toString());
        }
        releaseId();
    }

    public String addSpace(String prefix, int level){
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<level;i++){
            sb.append(i==level-1? "|"+prefix:"|   ");
        }

        return sb.toString();
    }

    public void releaseId(){
        TraceId traceId = traceIdHolder.get();
        if(traceId.isFirstLevel()){
            traceIdHolder.remove();
        }else{
            traceIdHolder.set(traceId.createPreviousId());
        }
    };
}
