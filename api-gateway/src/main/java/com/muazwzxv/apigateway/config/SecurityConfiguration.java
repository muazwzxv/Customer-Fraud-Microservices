package com.muazwzxv.apigateway.config;

import com.muazwzxv.apigateway.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtConfig config;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                // make sure we use stateless session; session won't be used to store user's state.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // handle an authorized attempts
                .exceptionHandling().authenticationEntryPoint((request, response, authenticationException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                // Add a filter to validate the tokens with every request
                .addFilterAfter(new JwtAuthenticationFilter(this.config), UsernamePasswordAuthenticationFilter.class)
                // authorization requests config
                .authorizeRequests()
                // allow all who are accessing "auth" service
                .antMatchers(HttpMethod.POST, this.config.getUri()).permitAll()
                // must be an admin if trying to access admin area (authentication is also required here)
//                .antMatchers("/gallery" + "/admin/**").hasRole("ADMIN")
                // Any other request must be authenticated
                .anyRequest().authenticated();
    }
}
