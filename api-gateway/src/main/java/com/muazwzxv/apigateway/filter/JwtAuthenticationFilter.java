package com.muazwzxv.apigateway.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.muazwzxv.apigateway.config.JwtConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtConfig config;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String header = request.getHeader(config.getHeader());

        if (header == null || !header.startsWith(config.getPrefix())) {
            chain.doFilter(request, response); // go to next filter
            return;
        }

        String token = header.replace(config.getPrefix(), "");

        try {
            DecodedJWT decoded = config.verifier().verify(token);

            String username = decoded.getClaim("username").asString();
            if (username != null) {
                List<String> authorities = decoded.getClaim("roles").asList(String.class);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                );

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (JWTVerificationException exception) {
            SecurityContextHolder.clearContext();
        }

        chain.doFilter(request, response);

    }
}
