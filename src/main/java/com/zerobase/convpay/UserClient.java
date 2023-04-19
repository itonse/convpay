package com.zerobase.convpay;

import com.zerobase.convpay.config.Applicationconfig;
import com.zerobase.convpay.dto.PayCancelRequest;
import com.zerobase.convpay.dto.PayCancelResponse;
import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.service.ConveniencePayService;
import com.zerobase.convpay.type.ConvenienceType;
import com.zerobase.convpay.type.PayMethodType;

public class UserClient {     // * UML: '[사용자(클라이언트)]' -> [결제 서비스(편결이)] -> [머니어댑터] *   에서 '사용자 '(외부)
    public static void main(String[] args) {
        // 사용자는 Applicationconfig만 이용.
        Applicationconfig applicationconfig = new Applicationconfig();
        ConveniencePayService conveniencePayService = applicationconfig.conveniencePayServiceDiscountPayMethod();

        // G25, 결제 50원
        PayRequest payRequest = new PayRequest(PayMethodType.CARD,
                ConvenienceType.G25, 50);
        PayResponse payResponse = conveniencePayService.pay(payRequest);

        System.out.println(payResponse);

        // G25, 취소 500원
        PayCancelRequest payCancelRequest = new PayCancelRequest(PayMethodType.MONEY,
                ConvenienceType.G25, 500);
        PayCancelResponse payCancelResponse = conveniencePayService.payCancel(payCancelRequest);

        System.out.println(payCancelResponse);
    }
}
