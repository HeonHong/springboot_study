package hello.advanced1.v3;

import hello.advanced1.trace.TraceStatus;
import hello.advanced1.trace.logTrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId){
        TraceStatus status = null;

        try{
            status = trace.begin("OrderServiceV4.orderItem()");
            //파라미터로 받은 traceId가 아니라 status안에 있는 traceId를 보내야함.
            //왜냐하면 status에서 갱신된 traceId 정보를 가지고 있기 때문이다.
            orderRepository.save(itemId);
            trace.end(status);
        }catch (Exception e){
            trace.exception(status, e);
            throw e;//예외 꼭 다시 던저주어야 함.
        }

    }

}
