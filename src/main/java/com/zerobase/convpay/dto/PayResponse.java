package com.zerobase.convpay.dto;

import com.zerobase.convpay.type.PayResult;

public class PayResponse {   // 결제 응답
    // 결제 결과
    PayResult payResult;

    // 결제 성공 금액
    Integer paidAmount;

    // 생성자: 한번에 값을 채우는 객체를 만들수 있어서 편리해짐
    public PayResponse(PayResult payResult, Integer paidAmount) {
        this.payResult = payResult;
        this.paidAmount = paidAmount;
    }

    // getter & setter
    public PayResult getPayResult() {
        return payResult;
    }

    public void setPayResult(PayResult payResult) {
        this.payResult = payResult;
    }

    public Integer getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Integer paidAmount) {
        this.paidAmount = paidAmount;
    }
}
