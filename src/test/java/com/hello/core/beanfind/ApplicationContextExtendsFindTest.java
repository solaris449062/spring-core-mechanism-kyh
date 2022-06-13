package com.hello.core.beanfind;

import com.hello.core.discount.DiscountPolicy;
import com.hello.core.discount.FixedDiscountPolicy;
import com.hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("When found by parent type, if there are more than one child, it would throw an exception")
    void whenFindBeanByParentTypeAndIfMultipleChildren_throwsNoUniqueBeanDefinitionException() {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> ac.getBean(DiscountPolicy.class)).isInstanceOf(NoUniqueBeanDefinitionException.class);
    }

    @Test
    @DisplayName("When found by parent type, if there are more than one child, specifying name prevents exception")
    void whenFindBeanByParentTypeAndIfMultipleChildren_specifyingNameDoesNotThrowException() {
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("find bean by specifying subtype (not the best way)")
    void findBeanBySubtype() {
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("find all beans by specifying parent type")
    void findAllBeansByParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);

        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value " + beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("find all beans by specifying parent type - Object")
    void findAllBeansByParentType_Object() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);

        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value " + beansOfType.get(key));
        }
    }

    @Configuration
    static class TestConfig {

        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixedDiscountPolicy() {
            return new FixedDiscountPolicy();
        }
    }
}
