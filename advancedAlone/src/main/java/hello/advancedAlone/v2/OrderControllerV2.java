package hello.advancedAlone.v2;

import hello.advancedAlone.trace.TraceStatus;
import hello.advancedAlone.trace.helloTrace.HelloTrace1;
import hello.advancedAlone.trace.logTrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final LogTrace trace;

    @GetMapping("/v2/request")
    public String request(String itemId){
        TraceStatus status = null;

        try{
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            trace.end(status);
            return "OK";
        }catch(Exception e){
            trace.exception(status, e);
            throw e;
        }


    }

}
