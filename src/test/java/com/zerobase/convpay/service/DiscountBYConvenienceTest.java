package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.type.ConvenienceType;
import com.zerobase.convpay.type.PayMethodType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountBYConvenienceTest {
    DiscountBYConvenience discountBYConvenience = new DiscountBYConvenience();

    @Test
    void discountTest() {
        //given
        PayRequest payRequestG25 =
                new PayRequest(PayMethodType.MONEY, ConvenienceType.G25, 1000);
        PayRequest payRequestGU =
                new PayRequest(PayMethodType.MONEY, ConvenienceType.GU, 1000);
        PayRequest payRequestSEVEN =
                new PayRequest(PayMethodType.MONEY, ConvenienceType.SEVEN, 1000);

        //when
        Integer discountedAmountG25 = discountBYConvenience.getDiscountedAmount(payRequestG25);
        Integer discountedAmountGU = discountBYConvenience.getDiscountedAmount(payRequestGU);
        Integer discountedAmountSEVEN = discountBYConvenience.getDiscountedAmount(payRequestSEVEN);

        //then
        assertEquals(800, discountedAmountG25);
        assertEquals(900, discountedAmountGU);
        assertEquals(1000, discountedAmountSEVEN);
    }
}