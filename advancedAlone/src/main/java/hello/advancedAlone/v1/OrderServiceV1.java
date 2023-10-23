package hello.advancedAlone.v1;

import hello.advancedAlone.trace.TraceStatus;
import hello.advancedAlone.trace.helloTrace.HelloTrace1;
import hello.advancedAlone.trace.logTrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {
    private final OrderRepositoryV1 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId) {


        TraceStatus status = null;

        try {
            status = trace.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            trace.end(status);
        }catch(Exception e){
            trace.exception(status, e);
            throw e;

        }


    }
}
