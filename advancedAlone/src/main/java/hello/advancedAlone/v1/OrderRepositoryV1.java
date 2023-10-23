package hello.advancedAlone.v1;

import hello.advancedAlone.LogTraceConfig;
import hello.advancedAlone.trace.TraceStatus;
import hello.advancedAlone.trace.helloTrace.HelloTrace1;
import hello.advancedAlone.trace.logTrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final LogTrace trace;

    public void save(String itemId) {


        TraceStatus status = null;

        try {
            status = trace.begin("OrderController.repository()");

            if(itemId.equals("ex")){
                // 객체 상태가 메서드 호출을 처리하기에 적절치 않을 때 사용하는 exception
                // 여기서는 ex가 들어오면 진행하면 안되니까 이렇게 exception을 발생시키는 것이다.
                throw new IllegalStateException("예외발생");
            }

            sleep(1000);

            trace.end(status);
        }catch(Exception e){
            trace.exception(status, e);
            throw e;

        }




    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            //IterruptedException발생할 수 있는 이유
            //내가 Thread를 sleep 시켰는데 누군가가 깨울 수 있기 때문이다.
//            throw new RuntimeException(e);

            e.printStackTrace();
        }
    }
}
