package com.services.secondservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SecondserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondserviceApplication.class, args);
    }
}