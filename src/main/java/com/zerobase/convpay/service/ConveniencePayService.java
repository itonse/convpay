package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.*;
import com.zerobase.convpay.type.*;

public class ConveniencePayService {   // *편결이* 결제 서비스(편결이에서 가장 메인이 되는 서비스) (pay, payCancel 기능 제공)
    private final MoneyAdapter moneyAdapter = new MoneyAdapter();  // 머니 어댑터는 한번 만들고나서 바꾸면 안되기 때문에 final로 지정.
    private final CardAdapter cardAdapter = new CardAdapter();

    public PayResponse pay(PayRequest payRequest) {   // 결제 기능: 매개면수로 '결제요청'을 받아서
        PaymentInterface paymentInterface;   // 인터페이스 불러오기

        if (payRequest.getPayMethodType() == PayMethodType.CARD) {   // 결제수단이 카드이면
            paymentInterface = cardAdapter;     // 인터페이스에 카드어댑터 넣기
        } else {    // 결제수단이 현금이면
            paymentInterface = moneyAdapter;    // 인터페이스에 머니어댑터 넣기
        }

        PaymentResult paymentResult = paymentInterface.payment(payRequest.getPayAmount());   // 페이먼트를 호출하고 결제금액 넣기


        // fail fast 방법: 맨 아래 주석으로 설명

        if (paymentResult == PaymentResult.PAYMENT_FAIL) {
            return new PayResponse(PayResult.FAIL, 0);   // 결제가 실패됬으면 결제 결과가 FAIL이고, 지불한 금액은 0원이다.
        }

        // Success Case (성공케이스는 가장 마지막에)
        return new PayResponse(PayResult.SUCCESS, payRequest.getPayAmount());   // 성고했을 때는 결제 결과가 SUCCESS, 금액은 내가 요청한 금액
    }

    public PayCancelResponse payCancel(PayCancelRequest payCancelRequest) {  // 결제취소 기능 (결제요청 기능과 거의 비슷)
        PaymentInterface paymentInterface;   // 인터페이스 불러오기

        if (payCancelRequest.getPayMethodType() == PayMethodType.CARD) {   // 결제수단이 카드이면
            paymentInterface = cardAdapter;     // 인터페이스에 카드어댑터 넣기
        } else {    // 결제수단이 현금이면
            paymentInterface = moneyAdapter;    // 인터페이스에 머니어댑터 넣기
        }


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
