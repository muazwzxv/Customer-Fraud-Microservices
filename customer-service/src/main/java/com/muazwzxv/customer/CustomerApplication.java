package com.muazwzxv.customer;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {
                "com.muazwzxv.customer",
                "com.muazwzxv.amqp"
        }
)
@EnableEurekaClient
@EnableFeignClients(
        // Refer to package in client-list modules
        basePackages = "com.muazwzxv.clients"
)
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

    @Bean
    public ModelMapper objectMapper() {
        return new ModelMapper();
    }
}


