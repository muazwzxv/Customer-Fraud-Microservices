package com.muazwzxv.authService.service;

import com.muazwzxv.customer.Customer;
import com.muazwzxv.customer.exception.CustomerNotFoundException;
import com.muazwzxv.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final BCryptPasswordEncoder encoder;
    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = this.customerRepository.findByEmail(username)
                .orElseThrow(() -> new CustomerNotFoundException("email", username));

        return new User(customer.getEmail(), customer.getPassword(), new ArrayList<GrantedAuthority>());
    }
}
