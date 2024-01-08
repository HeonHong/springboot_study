package hello.aop.internalCall;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV0 {

    public void external(){

        //내부에서 internal 호출시
        //자바 언어에서 메서드 앞에 별도의 참조가 없으면 this라는 의미
        //따라서 실제 대상 객체(target)의 인스턴스 의미
        //프록시 적용 안됨.
        //프록시 방식의 AOP의 한계임
        //AspectJ를 사용하면 바이트코드가 들어가기 때문에 문제 없음
        log.info("call external");
        internal();//내부 메서드 호출(this.internal())
    }

    public void internal(){
        log.info("call internal");
    }
}
