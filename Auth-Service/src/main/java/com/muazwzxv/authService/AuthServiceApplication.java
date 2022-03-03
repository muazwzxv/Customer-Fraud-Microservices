package com.muazwzxv.authService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.muazwzxv.authService",
                "com.muazwzxv.apigateway",
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.muazwzxv.clients"
)
public class AuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }
}
