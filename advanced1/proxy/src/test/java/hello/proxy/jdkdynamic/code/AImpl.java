package hello.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AImpl implements AInterface{
    @Override
    public String call() {
        log.info("A 호출");
        return "a";
    }

    @Override
    public String call2() {
        log.info("A call2호출");

        return "a call2";
    }

    @Override
    public String call3(String arg) {
        log.info("A {} 받기", arg);
        return "A" +  arg + "받기";
    }
}
