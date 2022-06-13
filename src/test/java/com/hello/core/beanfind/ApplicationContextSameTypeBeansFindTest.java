package com.hello.core.beanfind;

import com.hello.core.member.MemberRepository;
import com.hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextSameTypeBeansFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("If there are beans of same type, ac.getBean() throws exception")
    void whenThereAreMultipleBeansOfSameType_throwsNoUniqueBeanDefinitionException() {
        Assertions.assertThatThrownBy(() -> {
            MemberRepository bean = ac.getBean(MemberRepository.class);
        }).isInstanceOf(NoUniqueBeanDefinitionException.class);
    }

    @Test
    @DisplayName("If there are beans of same type, provide name in the argument as well")
    void whenThereAreMultipleBeansOfSameType_providingNamePreventsException() {
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        Assertions.assertThat(memberRepository).isInstanceOf(MemoryMemberRepository.class);
    }

    @Test
    @DisplayName("Find all beans of the specific type")
    void findAllBeansByType() {
        Map<String, MemoryMemberRepository> beansOfType = ac.getBeansOfType(MemoryMemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
