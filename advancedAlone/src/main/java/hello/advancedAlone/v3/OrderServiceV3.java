package hello.advancedAlone.v3;

import hello.advancedAlone.trace.TraceStatus;
import hello.advancedAlone.trace.logTrace.LogTrace;
import hello.advancedAlone.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {
    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId) {


        AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
            @Override
            public Void call() {
                orderRepository.save(itemId);
                return null;
            }
        };

        template.execute("OrderService.orderItem()");


    }
}
