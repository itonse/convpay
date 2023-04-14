package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.dto.PayResult;

public class ConveniencePayService {   // *편결이* 결제 서비스(편결이에서 가장 메인이 되는 서비스)
    private final MoneyAdapter moneyAdapter = new MoneyAdapter();  // 머니 어댑터는 한번 만들고나서 바꾸면 안되기 때문에 final로 지정.

    public PayResponse pay(PayRequest payRequest) {   // 결제 기능: 매개면수로 '결제요청'을 받아서
        MoneyUseResult moneyUseResult =
                moneyAdapter.use(payRequest.getPayAmount());


        // fail fast 방법: 예외케이스를 먼저 처리하고(빨리 실패를 해버리고) else로 옳은 케이스 처리하기 -> 읽기편하고, 유지보수 용이

        // Method()

        // Exception case5
        // Exception case4
        // Exception case1
        // Exception case2
        // Exception case3

        // Success Case (Only one)


        if (moneyUseResult == MoneyUseResult.USE_FAIL) {    // 실패케이스 먼저 처리
            return new PayResponse(PayResult.FAIL, 0);
        }

        // Success Case
        return new PayResponse(PayResult.SUCCESS, payRequest.getPayAmount());   // 성공케이스는 가작 마지막에
    }

    public void payCancel() {  // 결제취소 기능

    }

}
