package com.muazwzxv.notifications;

import com.muazwzxv.amqp.RabbitMqProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication(
        scanBasePackages = {
                "com.muazwzxv.notifications",
                "com.muazwzxv.amqp"
        }
)
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(RabbitMqProducer producer, NotificationConfiguration config) {
        return args -> {
            producer.publish("Test", config.getInternalExchange(), config.getInternalNotificationRoutingKey());
        };
    }
}
