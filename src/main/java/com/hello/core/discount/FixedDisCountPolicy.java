package com.hello.core.discount;

import com.hello.core.member.Grade;
import com.hello.core.member.Member;

public class FixedDisCountPolicy implements DiscountPolicy {

    private int discountFixedAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixedAmount;
        } else {
            return 0;
        }
    }

}
