package hello.advancedAlone.v3;

import hello.advancedAlone.trace.TraceStatus;
import hello.advancedAlone.trace.logTrace.LogTrace;
import hello.advancedAlone.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

    private final LogTrace trace;

    public void save(String itemId) {


        AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
            @Override
            public Void call() {
                if(itemId.equals("ex")){
                    // 객체 상태가 메서드 호출을 처리하기에 적절치 않을 때 사용하는 exception
                    // 여기서는 ex가 들어오면 진행하면 안되니까 이렇게 exception을 발생시키는 것이다.
                    throw new IllegalStateException("예외발생");
                }

                sleep(1000);

                return null;
            }
        };

        template.execute("OrderRepository.save()");

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
