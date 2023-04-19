package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayRequest;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
// @Profile("production")    // 환경변수에 producton 이라는 프로필이 정의된 경우에만 이 빈 생성
public class DiscountByPayMethod implements DiscountInterface{

    @Override
    public Integer getDiscountedAmount(PayRequest payRequest) {
        System.out.println("DiscountByPayMethod called");
        switch (payRequest.getPayMethodType()) {

            case MONEY:
                return payRequest.getPayAmount() * 7 / 10;   // 현금이면 30% 할인

            case CARD:
                return payRequest.getPayAmount();  // 카드면 복잡해서 할인 없음
        }

        return payRequest.getPayAmount();  // 기본 리턴값
    }
}
