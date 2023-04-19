package com.zerobase.convpay.service;

import com.zerobase.convpay.type.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
// @Scope("singleton")    // 아래 클래스를 계속 바꿔야 될 때    3개 모두 빈을 꺼내올때마다 빈을 새로 만듦.
// @Scope("request")   // 요청에 따라서 계속 바꿔야 할 때
// @Scope("prototype")   // 요청에 따라서 계속 바뀜
public class CardAdapter implements PaymentInterface{  // 카드어댑터는 페이먼트인터페이스를 의존
    // 1. 인증
    public void authorization() {
        System.out.println("authorization success.");
    }

    // 2. 승인
    public void approval() {
        System.out.println("approval success.");
    }


    // 3. 매입
    public CardUseResult capture(Integer payAmount) {
        if (payAmount > 100) {    // 카드 매입금액이 100원이 넘어가면 실패
            // 실패 (fail fast)
            return CardUseResult.USE_FAIL;
        }

        return CardUseResult.USE_SUCCESS; // 성공
    }

    // 4. 매입 취소
    public CardUseCancelResult cancelCapture(Integer cancelAmount) {
        if (cancelAmount < 1000) {    // 카드 매입취소 금액이 1000원보다 적으면 실패
            return CardUseCancelResult.USE_CANCEL_FAIL;
        }

        return CardUseCancelResult.USE_CANCEL_SUCCESS;   // 성공
    }

    @Override
    public PayMethodType getPayMethodType() {
        return PayMethodType.CARD;
    }

    // 인터페이스를 상속받았으므로 메소드를 오버라이딩 해서 구현 필요
    @Override
    public PaymentResult payment(Integer payAmount) {   // 머니어댑터랑 다르게 복잡
        authorization();    // 인증
        approval();  // 승인
        CardUseResult cardUseResult = capture(payAmount);// 매입

        if (cardUseResult == CardUseResult.USE_FAIL) {
            return  PaymentResult.PAYMENT_FAIL;
        }

        return PaymentResult.PAYMENT_SUCCESS;
    }

    @Override
    public CancelPaymentResult cancelPayment(Integer cancelAmount) {
        CardUseCancelResult cardUseCancelResult = cancelCapture(cancelAmount);

        if (cardUseCancelResult == CardUseCancelResult.USE_CANCEL_FAIL) {
            return  CancelPaymentResult.CANCEL_PAYMENT_FAIL;
        }

        return CancelPaymentResult.CANCEL_PAYMENT_SUCCESS;
    }
}
