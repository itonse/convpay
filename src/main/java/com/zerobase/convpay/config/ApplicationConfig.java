package com.zerobase.convpay.config;

import com.zerobase.convpay.ConvpayApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
@ComponentScan(basePackageClasses = ConvpayApplication.class)   // 스캐닝 시작 할 위치. 하위 클래스들도 모두 스캐닝
public class ApplicationConfig {
    @Autowired
    private ApplicationContext applicationContext;

    public void getRecource() throws IOException {   // 스프링 프로젝트 내 Resource(파일 등)를 로딩할 때 사용하는 기능
        Resource resource = applicationContext.getResource("myTemplate.txt");

        System.out.println(resource.contentLength() + "");
    }
}