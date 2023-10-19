package hello.advancedAlone.v1;

import hello.advancedAlone.trace.TraceStatus;
import hello.advancedAlone.trace.helloTrace.HelloTrace1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;
    private final HelloTrace1 trace;

    @GetMapping("/v1/request")
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
