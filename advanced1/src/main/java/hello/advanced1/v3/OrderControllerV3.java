package hello.advanced1.v3;

import hello.advanced1.trace.TraceStatus;
import hello.advanced1.trace.logTrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {
    private final OrderServiceV3 orderService;
    private final LogTrace trace;

    @GetMapping("/v3/request")
    public String request(String itemId){

        TraceStatus status = null;

        try{
            status = trace.begin("OrderControllerV4.request()");
            orderService.orderItem(itemId);
            trace.end(status);
            return "OK";

        }catch (Exception e){
            trace.exception(status, e);
            throw e;//예외 꼭 다시 던저주어야 함.
        }
    }
}
