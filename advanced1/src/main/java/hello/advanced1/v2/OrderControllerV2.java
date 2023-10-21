package hello.advanced1.v2;

import hello.advanced1.trace.TraceId;
import hello.advanced1.trace.TraceStatus;
import hello.advanced1.trace.helloTrace.HelloTraceV1;
import hello.advanced1.trace.helloTrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {
    private final OrderServiceV2 orderService;
    //fianl이 아닐 때는 나오지 않는다. why?
    private final HelloTraceV2 trace;

    @GetMapping("/v2/request")
    public String request(String itemId){

        TraceStatus status = null;

        try{
            status = trace.begin("OrderControllerV2.request()");
            orderService.orderItem(itemId, status.getTraceId());
            trace.end(status);
            return "OK";

        }catch (Exception e){
            trace.exception(status, e);
            throw e;//예외 꼭 다시 던저주어야 함.
        }
    }
}
