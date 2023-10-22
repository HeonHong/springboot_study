package hello.advanced1.v5;



import hello.advanced1.trace.callback.TraceCallback;
import hello.advanced1.trace.callback.TraceTemplate;
import hello.advanced1.trace.logTrace.LogTrace;
import hello.advanced1.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {

    private TraceTemplate traceTemplate;

    public OrderRepositoryV5(LogTrace logTrace) {
        this.traceTemplate = new TraceTemplate(logTrace);
    }

    public void save(String itemId){

        traceTemplate.execute("OrderRepositoryV5.save()", new TraceCallback<>() {
            @Override
            public Object call() {
                //저장 로직
                if(itemId.equals("ex")){
                    throw new IllegalStateException("예외발생");
                }
                sleep(1000);

                return null;
            }
        });

    }

    private void sleep(int millis){
        try {
           Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
