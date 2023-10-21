package hello.advanced1.v2;

import hello.advanced1.trace.TraceId;
import hello.advanced1.trace.TraceStatus;
import hello.advanced1.trace.helloTrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 trace;

    public void orderItem(String itemId, TraceId traceId){
        TraceStatus status = null;

        try{
            status = trace.beginSync(traceId,"OrderServiceV2.orderItem()");
            //파라미터로 받은 traceId가 아니라 status안에 있는 traceId를 보내야함.
            //왜냐하면 status에서 갱신된 traceId 정보를 가지고 있기 때문이다.
            orderRepository.save(status.getTraceId(), itemId);
            trace.end(status);
        }catch (Exception e){
            trace.exception(status, e);
            throw e;//예외 꼭 다시 던저주어야 함.
        }

    }

}
