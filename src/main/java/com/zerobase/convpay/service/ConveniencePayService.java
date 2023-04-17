package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.*;
import com.zerobase.convpay.type.*;

public class ConveniencePayService {   // *편결이* 결제 서비스(편결이에서 가장 메인이 되는 서비스) (pay, payCancel 기능 제공)
    private final MoneyAdapter moneyAdapter = new MoneyAdapter();  // 머니 어댑터는 한번 만들고나서 바꾸면 안되기 때문에 final로 지정.
    private final CardAdapter cardAdapter = new CardAdapter();

    public PayResponse pay(PayRequest payRequest) {   // 결제 기능: 매개면수로 '결제요청'을 받아서
        CardUseResult cardUseResult;
        MoneyUseResult moneyUseResult;

        if (payRequest.getPayMethodType() == PayMethodType.CARD) {   // 결제수단:신용카드
            cardAdapter.authorization();  // 승인
            cardAdapter.approval();
            cardUseResult = cardAdapter.capture(payRequest.getPayAmount());
        } else {    // 결제수단: 현금
            moneyUseResult =
                    moneyAdapter.use(payRequest.getPayAmount());   // 머니어댑터를 통해서 머니를 use 하도록 요청을 주고 그 응답값에 따라서 아래 if문 처리
        }



        // fail fast 방법: 맨 아래 주석으로 설명

        if (cardUseResult == cardUseResult.USE_FAIL ||
                moneyUseResult == MoneyUseResult.USE_FAIL) {    // 실패케이스 먼저 처리
            return new PayResponse(PayResult.FAIL, 0);   // 결제가 실패됬으면 결제 결과가 FAIL이고, 지불한 금액은 0원이다.
        }

        // Success Case (성공케이스는 가장 마지막에)
        return new PayResponse(PayResult.SUCCESS, payRequest.getPayAmount());   // 성고했을 때는 결제 결과가 SUCCESS, 금액은 내가 요청한 금액
    }

    public PayCancelResponse payCancel(PayCancelRequest payCancelRequest) {  // 결제취소 기능 (결제요청 기능과 거의 비슷)
        MoneyUseCancelResult moneyUseCancelResult = moneyAdapter.useCancel(   // 머니어댑터를 통해서 사용취소를 함
                payCancelRequest.getPayCancelAmount());// 이 금액만큼 결제 취소를 함.

        // Fail Case
        if (moneyUseCancelResult == MoneyUseCancelResult.MONEY_USE_CANCEL_FAIL) {    // 결제취소 결과가 실패이면,
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
