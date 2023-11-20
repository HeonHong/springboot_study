package hello.proxy.postProcessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanPostProcessorTest {

    @Test
    void basicConfig(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);

        //beanA 이름으로 B객체가 bean으로 등록된다.
        B b = applicationContext.getBean("beanA", B.class);
        b.helloB();

        Assertions.assertThrows(NoSuchBeanDefinitionException.class, ()->applicationContext.getBean(A.class));

    }

    @Slf4j
    @Configuration
    static class BeanPostProcessorConfig{
        @Bean(name="beanA")
        public A a(){
            return new A();
        }

        @Bean
        public AtoBPostProcessor helloPostProcessor(){
            return new AtoBPostProcessor();
        }
    }

    @Slf4j
    static class A {
        public void helloA() {
            log.info("helloA");

        }
    }
    @Slf4j
    static class B{
        public void helloB(){
            log.info("helloB");
        }
    }

    @Slf4j
    static class AtoBPostProcessor implements BeanPostProcessor{
        //BeanPostProcessor의 내부에 default 로 되어있어서 무조건 override하지 않아도 된다.

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//            return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);

            log.info("beanName={} bean={}",beanName,bean);

            if(bean instanceof A){
                return new B();
            }
            return bean;
        }
    }
}
