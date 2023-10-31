package hello.advancedAlone.v3;

import hello.advancedAlone.trace.TraceStatus;
import hello.advancedAlone.trace.logTrace.LogTrace;
import hello.advancedAlone.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;
    private final LogTrace trace;

    @GetMapping("/v3/request")
    public String request(String itemId){

        AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {
            @Override
            public String call() {
                orderService.orderItem(itemId);
                return "Okay";
            }
        };

        String result =  template.execute("OrderController.request()");
        return result;
    }

}
