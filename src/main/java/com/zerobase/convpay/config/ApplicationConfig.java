package com.zerobase.convpay.config;

import com.zerobase.convpay.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
public class ApplicationConfig {   // 사용자(클라이언트는) 요것만 가지고 전체 기능들을 사용해볼 수 있음. 여기서 전체 클래스를 수정 가능  -->더욱 객체지향적으로 변경

    @Bean   // conveniencePayService 이름으로 Bean을 등록
    public ConveniencePayService conveniencePayService() {    // 실질적으로는 메소드가 아니고 규약에 맞춘 설정이다. 객체타입과 동일한 이름(메소드니까 앞글자는 소문자)으로 하는게 관례.
        return new ConveniencePayService(   // 편결이(결제서비스)가 머니어댑터, 카드어댑터, DiscountByPayMethod 에 의존함 (코드 상에서는 의존성 없이 여기서 의존 클래스 변경)
                new HashSet<>(Arrays.asList(moneyAdapter(), cardAdapter())),
                discountByConvenience()
                //discountByPayMethod()   // 자유롭게 바꿀수 있음
        );
    }

    @Bean   // cardAdapter 이름으로 Bean을 등록.
    public CardAdapter cardAdapter() {
        return new CardAdapter();
    }

    @Bean
    public MoneyAdapter moneyAdapter() {   // 머니어댑터는 별도 메서드로 추출
        return new MoneyAdapter();
    }

    @Bean
    public DiscountByConvenience discountByConvenience() {   // Bean으로 등록 하려면 private는 안되고, public이여야만 함.
        return new DiscountByConvenience();
    }
}
