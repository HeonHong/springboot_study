package hello.advanced1.v1;

import hello.advanced1.trace.TraceStatus;
import hello.advanced1.trace.helloTrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {
    private final OrderServiceV1 orderService;
    //Q : fianl이 아닐 때는 나오지 않는다. why?
    //A : 위의 annotation이 @RequiredArgsConstructor로 되어있기 때문에 생성자를 만들 때 발생한 오류이다.
    private final HelloTraceV1 trace;

    @GetMapping("/v1/request")
    public String request(String itemId){

        TraceStatus status = null;

        try{

            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            trace.end(status);
            return "OK";

        }catch (Exception e){
            trace.exception(status, e);
            throw e;//예외 꼭 다시 던저주어야 함.
        }
    }
}
