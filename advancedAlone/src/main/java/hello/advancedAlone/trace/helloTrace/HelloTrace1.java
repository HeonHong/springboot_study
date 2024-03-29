package hello.advancedAlone.trace.helloTrace;

import hello.advancedAlone.trace.TraceId;
import hello.advancedAlone.trace.TraceStatus;
import hello.advancedAlone.trace.logTrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
public class HelloTrace1 implements LogTrace {


    private static final String START_PREFIX="-->";
    private static final String COMPLETE_PREFIX="<--";
    private static final String EX_PREFIX="<X-";

    private TraceId traceIdHolder;

    public TraceStatus begin(String message){

        if(traceIdHolder==null){
            traceIdHolder = new TraceId();
        }else{
            traceIdHolder = traceIdHolder.createNextId();
        }

        Long startMillis = System.currentTimeMillis();
        log.info("[{}] {}{}",traceIdHolder.getId(), addSpace(START_PREFIX,traceIdHolder.getLevel()),message);
        return new TraceStatus(traceIdHolder,startMillis,message);
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

        if(e==null){
            log.info("[{}] {}{} {}ms",traceIdHolder.getId(), addSpace(COMPLETE_PREFIX,traceIdHolder.getLevel()), status.getMessage(), time);
        }else{
            log.info("[{}] {}{} {}ms ex={}",traceIdHolder.getId(), addSpace(EX_PREFIX,traceIdHolder.getLevel()),status.getMessage(), time, e.toString());
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
        if(traceIdHolder.isFirstLevel()){
            traceIdHolder=null;
        }else{
            traceIdHolder=traceIdHolder.createPreviousId();
        }
    };
}
