package com.services.thirdservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class }) 
public class ThirdserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThirdserviceApplication.class, args);
    }
}
