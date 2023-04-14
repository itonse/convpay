package com.zerobase.convpay.dto;

public class PayRequest {   // 결제 요청    **패키지 dto는 외부와 통신? 하는 패키지
    // 편의점 종류
    ConvenienceType convenienceType;   // enum 타입 클래스

    // 결제 금액
    Integer payAmount;

    // 생성자: 한번에 값을 채우는 객체를 만들수 있어서 편리해짐
    public PayRequest(ConvenienceType convenienceType, Integer payAmount) {
        this.convenienceType = convenienceType;
        this.payAmount = payAmount;
    }


    // getter & setter
    public ConvenienceType getConvenienceType() {
        return convenienceType;
    }

    public void setConvenienceType(ConvenienceType convenienceType) {
        this.convenienceType = convenienceType;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }
}
