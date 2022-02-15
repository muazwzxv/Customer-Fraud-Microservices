package com.muazwzxv.customer;

import com.muazwzxv.amqp.RabbitMqConfiguration;
import com.muazwzxv.amqp.RabbitMqProducer;
import com.muazwzxv.clients.fraud.FraudCheckResponseDto;
import com.muazwzxv.clients.fraud.FraudClient;
import com.muazwzxv.clients.notifications.NotificationClient;
import com.muazwzxv.clients.notifications.NotificationRequestDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;
    private final RabbitMqProducer rabbitMq;
    private final RabbitMqConfiguration rabbitMqConfiguration;


    public void registerCustomer(CustomerRequestDto customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();

        // todo: check if email is valid & taken
        customerRepository.saveAndFlush(customer);

        // todo: check if fraudster
        FraudCheckResponseDto response = fraudClient.isFraudster(customer.getId());

        if (response != null) {
            if (response.isFraud()) throw new IllegalStateException("Fraud detected");
            log.info(response.toString());
        }

        NotificationRequestDto requestDto = NotificationRequestDto.builder()
                .senderId(customer.getId())
                .receiverId(customer.getId())
                .content("Customer is not a fraud")
                .build();

        rabbitMq.publish(requestDto, "internal.exchange", "internal.notification.routing-key");
    }
}
