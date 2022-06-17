package com.hello.core.singleton;

import com.hello.core.AppConfig;
import com.hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {
    @Test
    @DisplayName("DI container without Spring")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. create an object when called
        MemberService memberService1 = appConfig.memberService();

        // 2. create an object when called
        MemberService memberService2 = appConfig.memberService();

        // check the reference is different (they are indeed different objects)
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }
}
