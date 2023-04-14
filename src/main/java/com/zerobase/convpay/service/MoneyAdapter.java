package com.zerobase.convpay.service;

import com.zerobase.convpay.type.MoneyUseCancelResult;
import com.zerobase.convpay.type.MoneyUseResult;

public class MoneyAdapter {   // 결제 서비스 -> 머니 어댑터
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
}
