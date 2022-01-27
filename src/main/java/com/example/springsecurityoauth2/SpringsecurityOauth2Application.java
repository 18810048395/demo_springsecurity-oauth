package com.example.springsecurityoauth2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.springsecurityoauth2.dao")//使用MapperScan批量扫描所有的Mapper接口；
public class SpringsecurityOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecurityOauth2Application.class, args);
    }

}
