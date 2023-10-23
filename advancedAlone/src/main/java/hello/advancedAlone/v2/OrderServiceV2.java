package hello.advancedAlone.v2;

import hello.advancedAlone.trace.TraceStatus;
import hello.advancedAlone.trace.helloTrace.HelloTrace1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
    private final OrderRepositoryV2 orderRepository;
    private final HelloTrace1 trace;

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
