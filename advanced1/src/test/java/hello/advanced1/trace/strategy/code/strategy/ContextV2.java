package hello.advanced1.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/*
변형 전략 패턴
전략을 파라미터로 전달 받는 방식.
전략을 필드로 가지고 있지 않고 파라미터로
* */
@Slf4j
public class ContextV2 {

    public void execute(Strategy strategy){
        long startTime=System.currentTimeMillis();
        //비지니스로직 실행
        strategy.call();//위임
        //비지니스로직 종료
        long endTime=System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    };
}
