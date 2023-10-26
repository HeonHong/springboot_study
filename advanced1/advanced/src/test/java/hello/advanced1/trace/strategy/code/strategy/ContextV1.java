package hello.advanced1.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/*
* 필드에 전략 보관하는 방식으로 진행
* 전략 패턴에서 탬플릿 역할을 하는 파일이고, 이러한 부분을 컨택스트라고 한다.
* */
@Slf4j
public class ContextV1 {

    private Strategy strategy;

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute(){
        long startTime=System.currentTimeMillis();
        //비지니스로직 실행
        strategy.call();//위임
        //비지니스로직 종료
        long endTime=System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    };
}
