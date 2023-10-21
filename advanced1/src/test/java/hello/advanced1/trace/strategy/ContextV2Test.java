package hello.advanced1.trace.strategy;

import hello.advanced1.trace.strategy.code.strategy.ContextV2;
import hello.advanced1.trace.strategy.code.strategy.Strategy;
import hello.advanced1.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced1.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    //인강 듣기 전 먼저 혼자 해보기
    @Test
    void StrategyAlone(){
        ContextV2 context = new ContextV2();
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("변형 전략패턴 비지니스 로직1 실행");
            }
        });

        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("변형 전략패턴 비지니스 로직2 실행");
            }
        });

    }

    @Test
    void strategyV1(){
        ContextV2 contextV2 = new ContextV2();

        contextV2.execute(new StrategyLogic1());
        contextV2.execute(new StrategyLogic2());
    }
    @Test
    void strategyV2(){
        ContextV2 contextV2 = new ContextV2();

        contextV2.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        });
        contextV2.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직2 실행");
            }
        });
    }

//    람다식
    @Test
    void strategyV3(){
        ContextV2 contextV2 = new ContextV2();

        contextV2.execute(() -> log.info("비지니스 로직1 실행"));
        contextV2.execute(() -> log.info("비지니스 로직2 실행"));
    }
}
