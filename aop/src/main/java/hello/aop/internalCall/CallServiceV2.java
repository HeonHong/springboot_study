package hello.aop.internalCall;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV2 {

    //이건 기능을 너무 많이 가지고 있음
//    private final ApplicationContext applicationContext;
//
//    public CallServiceV2(ApplicationContext applicationContext) {
//        this.applicationContext = applicationContext;
//
//    }


    //싱글톤이면 컨테이너에서 꺼내주는 역할??
    //객체를 스프링 컨테이너에서 조회하는 것을 스프링 빈 생성 시점이 아니라 실제 객체를 사용하는 시점으로 지연 가능
    private final ObjectProvider<CallServiceV2> callServiceProvider;

    public CallServiceV2(ObjectProvider<CallServiceV2> callServiceProvider) {
        this.callServiceProvider = callServiceProvider;
    }

    public void external(){

        log.info("call external");
//        CallServiceV2 callServiceV2 = applicationContext.getBean(CallServiceV2.class);



        CallServiceV2 callServiceV2 = callServiceProvider.getObject();
        callServiceV2.internal();//내부 메서드 호출(this.internal())
    }

    public void internal(){
        log.info("call internal");
    }
}
