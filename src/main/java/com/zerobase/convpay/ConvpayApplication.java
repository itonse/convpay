package com.zerobase.convpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConvpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConvpayApplication.class, args);
			// 스프링부트가 부트스트랩하는 방식: 패키지 하위 클래스들 중에 Bean으로 등록할 애들을 모두 등록해서 SpringApplication에 띄움.
	}

}
