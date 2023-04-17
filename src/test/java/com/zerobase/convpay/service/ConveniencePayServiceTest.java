package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayCancelRequest;
import com.zerobase.convpay.dto.PayCancelResponse;
import com.zerobase.convpay.type.ConvenienceType;
import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.type.PayCancelResult;
import com.zerobase.convpay.type.PayMethodType;
import com.zerobase.convpay.type.PayResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConveniencePayServiceTest {
    ConveniencePayService conveniencePayService = new ConveniencePayService();

    @Test
    void pay_success() {   // 테스트 성공!
        //given
        PayRequest payRequest = new PayRequest(PayMethodType.MONEY, ConvenienceType.G25, 50);

        //when
        PayResponse payResponse = conveniencePayService.pay(payRequest);

        //then
        assertEquals(PayResult.SUCCESS,  payResponse.getPayResult());
        assertEquals(50, payResponse.getPaidAmount());
    }

    @Test
    void pay_fail() {   // 테스트 실패
        //given
        PayRequest payRequest = new PayRequest(PayMethodType.MONEY, ConvenienceType.G25, 1000_001);

        //when
        PayResponse payResponse = conveniencePayService.pay(payRequest);

        //then
        assertEquals(PayResult.FAIL,  payResponse.getPayResult());
        assertEquals(0, payResponse.getPaidAmount());   // 0원을 예상했는데 == payResponse.getPaidAmount() 원이 나왔다
    }


    @Test
    void pay_cancel_success() {   // 테스트 성공!
        //given
        PayCancelRequest payCancelRequest = new PayCancelRequest(PayMethodType.MONEY, ConvenienceType.G25, 1000);

        //when
        PayCancelResponse payCancelResponse = conveniencePayService.payCancel(payCancelRequest);

        //then
        assertEquals(PayCancelResult.PAY_CANCEL_SUCCESS,  payCancelResponse.getPayCancelResult());
        assertEquals(1000, payCancelResponse.getPayCanceledAmount());
    }

    @Test
    void pay_cancel_fail() {   // 테스트 실패
        //given
        PayCancelRequest payCancelRequest = new PayCancelRequest(PayMethodType.MONEY, ConvenienceType.G25, 99);

        //when
        PayCancelResponse payCancelResponse = conveniencePayService.payCancel(payCancelRequest);

        //then
        assertEquals(PayCancelResult.PAY_CANCEL_FAIL,  payCancelResponse.getPayCancelResult());
        assertEquals(0, payCancelResponse.getPayCanceledAmount());
    }
}
