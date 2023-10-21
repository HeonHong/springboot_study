package hello.advanced1.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

    public void execute(){

            long startTime=System.currentTimeMillis();
            //비지니스로직 실행
            call();
            //비지니스로직 종료
            long endTime=System.currentTimeMillis();
            long resultTime = endTime - startTime;
            log.info("resultTime={}", resultTime);
    };

    //protected면 다른 패키지일 경우, 상속 클래스만 접근 가능
    protected abstract void call();
}
