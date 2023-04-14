package com.zerobase.convpay.service;

import com.zerobase.convpay.type.ConvenienceType;
import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.type.PayResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConveniencePayServiceTest {
    ConveniencePayService conveniencePayService = new ConveniencePayService();

    @Test
    void pay_success() {   // 테스트 성공!
        //given
        PayRequest payRequest = new PayRequest(ConvenienceType.G25, 50);

        //when
        PayResponse payResponse = conveniencePayService.pay(payRequest);

        //then
        assertEquals(PayResult.SUCCESS,  payResponse.getPayResult());
        assertEquals(50, payResponse.getPaidAmount());
    }

    @Test
    void pay_fail() {   // 테스트 실패
        //given
        PayRequest payRequest = new PayRequest(ConvenienceType.G25, 1000_001);

        //when
        PayResponse payResponse = conveniencePayService.pay(payRequest);

        //then
        assertEquals(PayResult.FAIL,  payResponse.getPayResult());
        assertEquals(0, payResponse.getPaidAmount());   // 0원을 예상했는데 == payResponse.getPaidAmount() 원이 나왔다
    }
}
