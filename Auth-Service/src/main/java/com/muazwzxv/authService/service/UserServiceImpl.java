package com.muazwzxv.authService.service;

import com.muazwzxv.clients.customer.CustomerClient;
import com.muazwzxv.clients.customer.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final CustomerClient customerClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomerDTO customer = this.customerClient.getByEmail(username)
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        return new User(customer.getEmail(), customer.getPassword(), new ArrayList<GrantedAuthority>());
    }
}
