package com.muazwzxv.apigateway.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@Data
public class JwtConfig {

    @Value("${security.jwt.header}")
    private String header;

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.uri}")
    private String uri;

    @Value("${security.jwt.expiration}")
    private int expiration;

    @Value("${security.jwt.prefix}")
    private String prefix;

    @Bean
    public JWTVerifier verifier() {
        return JWT.require(Algorithm.HMAC256(this.getSecret())).build();
    }

}
