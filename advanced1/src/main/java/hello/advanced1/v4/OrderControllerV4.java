package hello.advanced1.v4;

import hello.advanced1.trace.TraceStatus;
import hello.advanced1.trace.logTrace.LogTrace;
import hello.advanced1.trace.template.AbstractTemplate;
import hello.advanced1.v3.OrderServiceV3;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {
    private final OrderServiceV4 orderService;
    private final LogTrace trace;

    @GetMapping("/v4/request")
    public String request(String itemId){


        AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {
            @Override
            protected String call() {
                orderService.orderItem(itemId);
                return "OK";
            }
        };

       return template.execute("OrderControllerV4.request()");

    }
}
