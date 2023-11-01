package hello.proxy.config.v1_proxy.interface_proxy;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements OrderControllerV1 {

    private final OrderControllerV1 target;
    private final LogTrace logTrace;
    private String cacheValue;

    @Override
    public String request(String itemId) {


        if(cacheValue==null){
            System.out.println("null로 들어옴");
            TraceStatus status=null;

        try {
            status =  logTrace.begin("OrderContoller.request()");

            //target호출
            String result = target.request(itemId);
            cacheValue=result;
            logTrace.end(status);
            return result;
        }catch (Exception e){
            logTrace.exception(status,e);
            throw e;
        }
        }else{
            System.out.println("cache로 들어옴");
            return cacheValue;
        }

    }

    @Override
    public String noLog() {

        return target.noLog();
    }
}
