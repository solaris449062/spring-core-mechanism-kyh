package com.hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: user A makes an order with $10.
//        statefulService1.order("userA", 10);
        int userAPrice = statefulService1.order("userA", 10);

        // ThreadB: user B makes an order with $20.
//        statefulService2.order("userB", 20);
        int userBPrice = statefulService2.order("userB", 20);

        // ThreadA: check order information for userA.
//        int price = statefulService1.getPrice();

        // ThreadA: user A expects that the order is $10. However, it became $20.
        System.out.println("price = " + userAPrice);

        // Check that it indeed becomes $20, because StatefulService bean is singleton and shared to user B as well.
//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
