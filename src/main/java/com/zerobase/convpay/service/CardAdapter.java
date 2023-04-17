package com.zerobase.convpay.service;

import com.zerobase.convpay.type.CardUseCancelResult;
import com.zerobase.convpay.type.CardUseResult;

public class CardAdapter {
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
        if (payAmount > 100) {    // 매입금액이 100원이 넘어가면 실패
            // 실패 (fail fast)
            return CardUseResult.USE_FAIL;
        }

        return CardUseResult.USE_SUCCESS; // 성공
    }

    // 4. 매입 취소
    public CardUseCancelResult cancelCapture(Integer cancelAmount) {
        if (cancelAmount < 1000) {    // 매입취소 금액이 1000원보다 적으면 실패
            return CardUseCancelResult.USE_CANCEL_FAIL;
        }

        return CardUseCancelResult.USE_CANCEL_SUCCESS;   // 성공
    }

}
