package com.zerobase.convpay.config;

import com.zerobase.convpay.service.*;

import java.util.Arrays;
import java.util.HashSet;

public class Applicationconfig {   // 사용자(클라이언트는) 요것만 가지고 전체 기능들을 사용해볼 수 있음. 여기서 전체 클래스를 수정 가능  -->더욱 객체지향적으로 변경

    public ConveniencePayService conveniencePayServiceDiscountConvenience() {
        return new ConveniencePayService(   // 편결이(결제서비스가 머니어댑터, 카드어댑터, DiscountByPayMethod에 의존함
                new HashSet<>(
                        Arrays.asList(new MoneyAdapter(), new CardAdapter())
                ),
                new DiscountBYConvenience()
        );
    }


    public ConveniencePayService conveniencePayServiceDiscountPayMethod() {
        return new ConveniencePayService(   // 편결이(결제서비스가 머니어댑터, 카드어댑터, DiscountByPayMethod에 의존함
                new HashSet<>(
                        Arrays.asList(new MoneyAdapter(), new CardAdapter())
                ),
                new DiscountByPayMethod()
        );
    }

}
