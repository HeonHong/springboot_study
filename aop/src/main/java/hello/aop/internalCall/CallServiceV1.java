package hello.aop.internalCall;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    //순환참조 문제가 발생함
//    @Autowired
//    public CallServiceV1(CallServiceV1 callServiceV1) {
//        this.callServiceV1 = callServiceV1;
//    }

    @Autowired
    public void setCallServiceV1(@Lazy CallServiceV1 callServiceV1){
        //계속 뜨면 @Lazy로 지연로딩
        log.info("callServiceV1  setter={}", callServiceV1.getClass());
        this.callServiceV1=callServiceV1;
    }

    public void external(){

        log.info("call external");
        callServiceV1.internal();//내부 메서드 호출(this.internal())
    }

    public void internal(){
        log.info("call internal");
    }
}
