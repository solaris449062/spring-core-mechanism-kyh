package com.hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    private SingletonService() {
    }

    public static SingletonService getInstance() {
        return instance;
    }

    public void logic() {
        System.out.println("Singleton object logic called");
    }

    @Test
    @DisplayName("Example of using an object with singleton pattern")
    void singletonServiceTest() {

        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }
}
