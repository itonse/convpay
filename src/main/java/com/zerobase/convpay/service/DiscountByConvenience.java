package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayRequest;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
// @Profile("test")   // 환경변수에 test라는 프로필이 정의된 경우에만 이 빈 생성
@Primary  // 할인방법 두개 중에 편의점에 따른 할인을 우선순위로 하겠다.
public class DiscountByConvenience implements DiscountInterface {

    @Override
    public Integer getDiscountedAmount(PayRequest payRequest) {
        System.out.println("DiscountByConvenience called");
        // 편의점이 3개가 있음
        switch (payRequest.getConvenienceType()) {
            case G25:
                return payRequest.getPayAmount() * 8 / 10;   // G25는 20% 할인
            case GU:
                return payRequest.getPayAmount() * 9 / 10;   // GU는 10% 할인
            case SEVEN:
                return payRequest.getPayAmount();   // SEVEN은 할인이 없음
        }

        return payRequest.getPayAmount();   // 기본 도달 값 (여기까지는 도달 안함)
    }

}
