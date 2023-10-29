package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

public class OrderServiceConcreteProxy extends OrderServiceV2 {


    private final OrderServiceV2 target;
    private final LogTrace logTrace;

    public OrderServiceConcreteProxy(OrderServiceV2 target, LogTrace logTrace) {
        super(null);//위의 기능을 사용할 것이 아니니까 그냥 null로 줘도 상관없다.
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void orderItemId(String itemId) {
        TraceStatus status=null;

        try {
            status =  logTrace.begin("OrderService.orderItemId");

            //target호출
            target.orderItemId(itemId);
            logTrace.end(status);
        }catch (Exception e){
            logTrace.exception(status,e);
            throw e;
        }
    }
}
