package com.muazwzxv.customer;

import com.muazwzxv.amqp.RabbitMqConfiguration;
import com.muazwzxv.amqp.RabbitMqProducer;
import com.muazwzxv.clients.customer.CustomerDTO;
import com.muazwzxv.clients.fraud.FraudCheckResponseDto;
import com.muazwzxv.clients.fraud.FraudClient;
import com.muazwzxv.clients.notifications.NotificationClient;
import com.muazwzxv.clients.notifications.NotificationRequestDto;
import com.muazwzxv.customer.exception.CustomerNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;
    private final RabbitMqProducer rabbitMq;
    private final RabbitMqConfiguration rabbitMqConfiguration;
    private final ModelMapper mapper;


    public CustomerDTO getCustomerById(Long id) {
        Customer customer = this.customerRepository
                .findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("id", id));
        return this.customerToDTO(customer);
    }

    public CustomerDTO getCustomerByUuid(UUID uuid) {
        Customer customer = this.customerRepository
                .findByUuid(uuid)
                .orElseThrow(() -> new CustomerNotFoundException("uuid", uuid));
        return this.customerToDTO(customer);
    }

    public List<CustomerDTO> getAllCustomer() {
        List<Customer> customers = this.customerRepository.findAll();
        return customers.stream()
                .map(this::customerToDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO createCustomer(CustomerRequestDto req) {

        if (this.customerRepository.findByEmail(req.email()).isPresent()) {
            throw new
        }

        Customer customer = Customer.builder()
                .firstName(req.firstName())
                .lastName(req.lastName())
                .email(req.email())
                .build();

        // todo: check if email is valid & taken
        customerRepository.saveAndFlush(customer);

        return this.customerToDTO(customer);
    }

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

    // Method for modelMapper

    private CustomerDTO customerToDTO(Customer customer) {
        return mapper.map(customer, CustomerDTO.class);
    }

    private Customer dtoToCustomer(CustomerDTO dto) {
        return mapper.map(dto, Customer.class);
    }
}
