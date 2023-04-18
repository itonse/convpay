package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayRequest;

public class DiscountBYConvenience implements DiscountInterface {

    @Override
    public Integer getDiscountedAmount(PayRequest payRequest) {
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
