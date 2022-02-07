package com.muazwzxv.fraud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FraudCheckApplication {
    public static void main(String[] args) {
        SpringApplication.run(FraudCheckApplication.class, args);
    }
}
