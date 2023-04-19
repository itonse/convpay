package com.zerobase.convpay.service;

import com.zerobase.convpay.type.CancelPaymentResult;
import com.zerobase.convpay.type.PayMethodType;
import com.zerobase.convpay.type.PaymentResult;

public interface PaymentInterface {  // '머니어댑터'와 '카드어댑터'가 이 인터페이스를 의존하도록 한다. (DIP 의존관계역전원칙 구현)
    PayMethodType getPayMethodType();
    PaymentResult payment(Integer payAmount);   // 결제
    CancelPaymentResult cancelPayment(Integer cancelAmount);// 결제취소


}
