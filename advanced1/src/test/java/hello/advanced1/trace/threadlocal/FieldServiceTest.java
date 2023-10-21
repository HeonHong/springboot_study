package hello.advanced1.trace.threadlocal;

import hello.advanced1.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService= new FieldService();

    @Test
    void field(){
        log.info("main Start");
        Runnable userA = ()->{
            fieldService.logic("userA");
        };

//        Runnable userA = new Runnable() {
//            @Override
//            public void run() {
//                fieldService.logic("userB");
//            }
//        };

        Runnable userB = ()->{
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("threadA");

        Thread threadB = new Thread(userB);
        threadA.setName("threadB");

        threadA.start();
//        sleep(2000);//동시성 문제 아예 발생 안함
        sleep(50);//동시성 문제 발생
        threadB.start();
        sleep(2000);//메인 쓰레드 종료 대기
        log.info("main exit");

    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
