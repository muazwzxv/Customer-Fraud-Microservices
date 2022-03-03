package com.muazwzxv.authService.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.muazwzxv.apigateway.config.JwtConfig;
import com.muazwzxv.customer.Customer;
import com.muazwzxv.customer.exception.CustomerNotFoundException;
import com.muazwzxv.customer.repository.CustomerRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JwtUsernamePasswordAuthenticator extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager auth;
    private final JwtConfig config;
    private final CustomerRepository customerRepository;

    public JwtUsernamePasswordAuthenticator(AuthenticationManager auth, JwtConfig config, CustomerRepository customerRepository) {
        this.auth = auth;
        this.config = config;
        this.customerRepository = customerRepository;

        // by default Spring Security use /login
        // Change to /auth
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(config.getUri(), "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
            return this.auth.authenticate(token);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        try {
            Algorithm algo = Algorithm.HMAC256(config.getSecret());

            Customer customer = this.customerRepository.findByEmail(authResult.getName()).orElseThrow(() -> new CustomerNotFoundException("email", authResult.getName()));

            String token = JWT.create()
                    .withClaim("email", customer.getEmail())
                    .withClaim("uuid", customer.getUuid().toString())
                    .withExpiresAt(new Date(System.currentTimeMillis() + config.getExpiration() * 1000L))
                    .sign(algo);

            response.addHeader(config.getHeader(), config.getPrefix() + token);
        } catch (CustomerNotFoundException | JWTCreationException e) {
            throw new RuntimeException(e);
        }
    }
}
