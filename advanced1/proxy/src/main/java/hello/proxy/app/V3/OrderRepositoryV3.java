package hello.proxy.app.V3;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV3 {

    public void save(String itemId) {
        //저장로직
        if(itemId.equals("ex")){
            throw new IllegalStateException("예외발생");
        }

        sleep(1000);

    }

    void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
