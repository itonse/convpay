package com.zerobase.convpay.service;

public class MoneyAdapter {   // 결제 서비스 -> 머니 어댑터
    public MoneyUseResult use(Integer payAmount) {   // 머니 사용
        System.out.println("MoneyAdapter.use : " + payAmount);

        if (payAmount > 1000_000) {   // 너무 큰 금액은 실패
            return MoneyUseResult.USE_FAIL;
        }
        return MoneyUseResult.USE_SUCCESS;  // 그 외는 무조건 성공
    }



    public void useCancel() {   // 머니 사용취소

    }
}
