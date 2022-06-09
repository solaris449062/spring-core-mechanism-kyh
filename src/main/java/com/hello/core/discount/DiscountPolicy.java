package com.hello.core.discount;

import com.hello.core.member.Member;

public interface DiscountPolicy {

    /*
    *
    * @return how much is discounted
    * */
    int discount(Member member, int price);
}
