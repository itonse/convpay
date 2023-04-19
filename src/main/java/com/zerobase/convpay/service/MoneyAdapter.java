package com.zerobase.convpay.service;

import com.zerobase.convpay.type.*;
import org.springframework.stereotype.Component;

@Component
public class MoneyAdapter implements PaymentInterface{   // 머니어댑터는 페이먼트인터페이스를 의존
    public MoneyUseResult use(Integer payAmount) {   // 머니 사용
        System.out.println("MoneyAdapter.use : " + payAmount);

        //fail fast 적용
        if (payAmount > 1000_000) {   // 너무 큰 금액을 사용할때는 실패
            return MoneyUseResult.USE_FAIL;
        }
        return MoneyUseResult.USE_SUCCESS;  // 그 외는 무조건 성공
    }



    public MoneyUseCancelResult useCancel(Integer payCancelAmount) {   // 머니 사용취소
        System.out.println("MoneyAdapter.useCancel : " + payCancelAmount);

        //fail fast 적용
        if (payCancelAmount < 100) {   // 너무 작은 금액을 취소하려고 하면 실패
            return MoneyUseCancelResult.MONEY_USE_CANCEL_FAIL;
        }
        return MoneyUseCancelResult.MONEY_USE_CANCEL_SUCCESS;  // 그 외는 무조건 성공
    }


    @Override
    public PayMethodType getPayMethodType() {
        return PayMethodType.MONEY;
    }

    // 인터페이스를 상속받았으므로 메소드를 오버라이딩 해서 구현 필요
    @Override
    public PaymentResult payment(Integer payAmount) {
        MoneyUseResult moneyUseResult = use(payAmount);

        if (moneyUseResult == MoneyUseResult.USE_FAIL) {
            return PaymentResult.PAYMENT_FAIL;
        }

        return PaymentResult.PAYMENT_SUCCESS;
    }

    @Override
    public CancelPaymentResult cancelPayment(Integer cancelAmount) {
        MoneyUseCancelResult moneyUseCancelResult = useCancel(cancelAmount);

        if (moneyUseCancelResult == MoneyUseCancelResult.MONEY_USE_CANCEL_FAIL) {
            return CancelPaymentResult.CANCEL_PAYMENT_FAIL;
        }

        return CancelPaymentResult.CANCEL_PAYMENT_SUCCESS;
    }
}
