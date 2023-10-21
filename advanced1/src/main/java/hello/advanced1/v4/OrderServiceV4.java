package hello.advanced1.v4;

import hello.advanced1.trace.TraceStatus;
import hello.advanced1.trace.logTrace.LogTrace;
import hello.advanced1.trace.template.AbstractTemplate;
import hello.advanced1.v3.OrderRepositoryV3;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

    private final OrderRepositoryV4 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId){
        AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
            @Override
            protected Void call() {
                orderRepository.save(itemId);
                return null;
            }
        };

       template.execute("OrderControllerV4.request()");

    }

}
