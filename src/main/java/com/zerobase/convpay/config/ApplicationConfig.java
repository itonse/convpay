package com.zerobase.convpay.config;

import com.zerobase.convpay.ConvpayApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ConvpayApplication.class)   // 스캐닝 시작 할 위치. 하위 클래스들도 모두 스캐닝
public class ApplicationConfig {

}