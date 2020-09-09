package com.services.thirdservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ThirdserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThirdserviceApplication.class, args);
    }
}
