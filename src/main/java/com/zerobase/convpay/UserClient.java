package com.zerobase.convpay;

import com.zerobase.convpay.config.Applicationconfig;
import com.zerobase.convpay.dto.PayCancelRequest;
import com.zerobase.convpay.dto.PayCancelResponse;
import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.service.ConveniencePayService;
import com.zerobase.convpay.type.ConvenienceType;
import com.zerobase.convpay.type.PayMethodType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserClient {     // * UML: '[사용자(클라이언트)]' -> [결제 서비스(편결이)] -> [머니어댑터] *   에서 '사용자 '(외부)
    public static void main(String[] args) {
        // 사용자는 스프링 안으로 접근
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(Applicationconfig.class);
            // 스프링 컨테이너를 관리하는 applicationContext를 만듦. 만들면서 구성 정보는 Applicationconfig.class 를 참고하도록 요청.

        ConveniencePayService conveniencePayService =
                applicationContext.getBean("conveniencePayService",
                        ConveniencePayService.class);
            // ConveniencePayService 에서 등록했던 Bean들을 가져옴.

        // G25, 결제 50원
        PayRequest payRequest = new PayRequest(PayMethodType.CARD,
                ConvenienceType.G25, 50);
        PayResponse payResponse = conveniencePayService.pay(payRequest);

        System.out.println(payResponse);

        // G25, 취소 500원
        PayCancelRequest payCancelRequest = new PayCancelRequest(PayMethodType.MONEY,
                ConvenienceType.G25, 500);
        PayCancelResponse payCancelResponse = conveniencePayService.payCancel(payCancelRequest);

        System.out.println(payCancelResponse);
    }
}
