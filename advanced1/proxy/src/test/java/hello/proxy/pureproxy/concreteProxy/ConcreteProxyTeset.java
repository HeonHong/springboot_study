package hello.proxy.pureproxy.concreteProxy;

import hello.proxy.pureproxy.concreteProxy.code.ConcreteClient;
import hello.proxy.pureproxy.concreteProxy.code.ConcreteLogic;
import hello.proxy.pureproxy.concreteProxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

import java.sql.Time;

public class ConcreteProxyTeset {

    @Test
    void NoProxy(){
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient client= new ConcreteClient(concreteLogic);
        client.exectue();
    }

    @Test
    void addProxy(){
        ConcreteLogic concreteLogic = new ConcreteLogic();
        TimeProxy timeProxy = new TimeProxy(concreteLogic);
        ConcreteClient client = new ConcreteClient(timeProxy);
        client.exectue();
    }
}
