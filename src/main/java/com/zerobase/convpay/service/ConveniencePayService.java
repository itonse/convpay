package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.*;
import com.zerobase.convpay.type.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConveniencePayService {   // *편결이* 결제 서비스(편결이에서 가장 메인이 되는 서비스) (pay, payCancel 기능 제공)
        // 편결이는 자기 본연의 업무만 수행함. (SRP 원칙 적용)
    private final Map<PayMethodType, PaymentInterface> paymentInterfaceMap =
            new HashMap<>();
        // 페이메소드 타입에 따라 페이먼트인터페이스를 키, 밸류로 갖는 맵 타입의 페이먼트인터페이스 맵 생성
    private final DiscountInterface discountInterface;

    public ConveniencePayService(Set<PaymentInterface> paymentInterfaceSet,
                                 DiscountInterface discountInterface) {
        paymentInterfaceSet.forEach(
                paymentInterface -> paymentInterfaceMap.put(
                        paymentInterface.getPayMethodType(),
                        paymentInterface
                )
        );

        this.discountInterface = discountInterface;
    }

    public PayResponse pay(PayRequest payRequest) {   // 결제 기능: 매개면수로 '결제요청'을 받아서
        PaymentInterface paymentInterface = paymentInterfaceMap.get(payRequest.getPayMethodType());
            // 맵 안에서 결제수단을 키로 가져옴

        Integer discountedAmount = discountInterface.getDiscountedAmount(payRequest);   // 먼저 결제수단별 할인 받기
        PaymentResult paymentResult = paymentInterface.payment(discountedAmount);   // 페이먼트를 호출하고 할인된 결제금액 넣기


        // fail fast 방법: 맨 아래 주석으로 설명

        if (paymentResult == PaymentResult.PAYMENT_FAIL) {
            return new PayResponse(PayResult.FAIL, 0);   // 결제가 실패됬으면 결제 결과가 FAIL이고, 지불한 금액은 0원이다.
        }

        // Success Case (성공케이스는 가장 마지막에)
        return new PayResponse(PayResult.SUCCESS, discountedAmount);   // 성고했을 때는 결제 결과가 SUCCESS, 금액은 내가 요청한 금액
    }

    public PayCancelResponse payCancel(PayCancelRequest payCancelRequest) {  // 결제취소 기능 (결제요청 기능과 거의 비슷)
        PaymentInterface paymentInterface =
                paymentInterfaceMap.get(payCancelRequest.getPayMethodType());
            // 맵 안에서 결제수단을 키로 가져옴

        CancelPaymentResult cancelPaymentResult =
                paymentInterface.cancelPayment(payCancelRequest.getPayCancelAmount());


        // Fail Case
        if (cancelPaymentResult == CancelPaymentResult.CANCEL_PAYMENT_FAIL) {    // 결제취소 결과가 실패이면,
            return new PayCancelResponse(PayCancelResult.PAY_CANCEL_FAIL, 0);  // 결제취소 실패로 결과를 주고, 결제취소된 금액은 0원
        }

        // Success Case
        return new PayCancelResponse(PayCancelResult.PAY_CANCEL_SUCCESS,  // 성공했을때는 결제취소 성공.
                payCancelRequest.getPayCancelAmount());    // 금액은 내가 요청한 금액.
    }

}

// fail fast 방법: 예외케이스를 먼저 처리하고(빨리 실패를 해버리고) else로 옳은 케이스 처리하기 -> 읽기편하고, 유지보수 용이

// Method()

// Exception case5
// Exception case4
// Exception case1
// Exception case2
// Exception case3

// Success Case (Only one)
