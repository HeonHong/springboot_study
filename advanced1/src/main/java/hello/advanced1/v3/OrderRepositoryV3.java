package hello.advanced1.v3;



import hello.advanced1.trace.TraceStatus;
import hello.advanced1.trace.logTrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

    private final LogTrace trace;

    public void save(String itemId){

        TraceStatus status = null;

        try{
            status = trace.begin("OrderRepositoryV4.save()");
            //저장 로직
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외발생");
            }
            sleep(1000);

            trace.end(status);
        }catch (Exception e){
            trace.exception(status, e);
            throw e;//예외 꼭 다시 던저주어야 함.
        }
    }

    private void sleep(int millis){
        try {
           Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
