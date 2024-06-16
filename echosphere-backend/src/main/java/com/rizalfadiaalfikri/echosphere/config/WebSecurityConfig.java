package com.rizalfadiaalfikri.echosphere.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(
                session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/api/v1/auth/**").authenticated()
                                .anyRequest().permitAll())
                .csrf(
                        csrf -> csrf.disable());

        return http.build();
    }

}
