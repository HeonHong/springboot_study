package hello.advanced1.trace.strategy;

import hello.advanced1.trace.strategy.code.strategy.ContextV1;
import hello.advanced1.trace.strategy.code.strategy.Strategy;
import hello.advanced1.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced1.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    void templateMethodV0(){
        logic1();
        logic2();
    }

    private void logic1(){
        long startTime=System.currentTimeMillis();
        //비지니스로직 실행
        log.info("비지니스 로직1 실행");
        //비지니스로직 종료
        long endTime=System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2(){
        long startTime=System.currentTimeMillis();
        //비지니스로직 실행
        log.info("비지니스 로직2 실행");
        //비지니스로직 종료
        long endTime=System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }


    /*전략패턴 사용*/
    @Test
    void strategyV1(){
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();

        contextV1 = new ContextV1(new StrategyLogic2());
        contextV1.execute();

    }

    @Test
    void strategy2(){
        Strategy strategyLogic1=new Strategy() {
            @Override
            public void call() {
                log.info("익명 내부 클래스 비지니스 로직1 실행");
            }
        };

        ContextV1 context = new ContextV1(strategyLogic1);
        log.info("strategyLogic1={}", strategyLogic1.getClass());
        context.execute();

        Strategy strategyLogic2=new Strategy() {
            @Override
            public void call() {
                log.info("익명 내부 클래스 비지니스 로직2 실행");
            }
        };

        ContextV1 context2 = new ContextV1(strategyLogic2);
        log.info("strategyLogic1={}", strategyLogic2.getClass());

        context2.execute();

    }

    @Test
    void strategy3(){


        ContextV1 context = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("익명 내부 클래스 비지니스 로직1 실행");
            }
        });
        context.execute();


        ContextV1 context2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("익명 내부 클래스 비지니스 로직2 실행");
            }
        });
        context2.execute();

    }

    @Test
    void strategy4(){


        ContextV1 context = new ContextV1(() -> log.info("익명 내부 클래스 비지니스 로직1 실행"));
        context.execute();

       context.setStrategy(new Strategy() {
            @Override
            public void call() {
                log.info("세터로 바꾸기");
            }
        });
       context.execute();

        ContextV1 context2 = new ContextV1(() -> log.info("익명 내부 클래스 비지니스 로직2 실행"));
        context2.execute();

    }


}
