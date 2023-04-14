package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.dto.PayResult;

public class ConveniencePayService {   // 결제 서비스(편결이에서 가장 메인이 되는 서비스)
    public PayResponse pay(PayRequest payRequest) {   // 결제 기능

        return new PayResponse(PayResult.SUCCESS, 100);  // 결제응답 클래스 객체 반환
    }

    public void payCancel() {  // 결제취소 기능

    }

}
