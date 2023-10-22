package hello.advanced1.v5;

import hello.advanced1.trace.callback.TraceCallback;
import hello.advanced1.trace.callback.TraceTemplate;
import hello.advanced1.trace.logTrace.LogTrace;
import hello.advanced1.trace.template.AbstractTemplate;
import hello.advanced1.v0.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate traceTemplate;

    @Autowired
    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.traceTemplate = new TraceTemplate(trace);
    }

    public void orderItem(String itemId){

        traceTemplate.execute("OrderControllerV5.request()", new TraceCallback<>() {
            @Override
            public Object call() {
                orderRepository.save(itemId);
                return null;
            }
        });


    }

}
