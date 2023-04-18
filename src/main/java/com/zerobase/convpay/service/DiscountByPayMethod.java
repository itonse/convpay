package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayRequest;

public class DiscountByPayMethod implements DiscountInterface{

    @Override
    public Integer getDiscountedAmount(PayRequest payRequest) {
        switch (payRequest.getPayMethodType()) {

            case MONEY:
                return payRequest.getPayAmount() * 7 / 10;   // 현금이면 30% 할인

            case CARD:
                return payRequest.getPayAmount();  // 카드면 복잡해서 할인 없음
        }

        return payRequest.getPayAmount();  // 기본 리턴값
    }
}
