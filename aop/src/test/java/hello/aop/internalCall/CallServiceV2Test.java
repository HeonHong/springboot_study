package hello.aop.internalCall;

import hello.aop.internalCall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV2Test {

    @Autowired
    CallServiceV2 callService;//프록시가 컨테이너에 저장됨.

    @Test
    void external() {
//        log.info("target={}", callServiceV0.getClass());
        callService.external();
    }

    @Test
    void internal() {
        callService.internal();
    }
}