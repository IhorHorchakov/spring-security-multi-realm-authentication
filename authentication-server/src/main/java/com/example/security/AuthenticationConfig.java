package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import static com.example.security.Authority.EXTERNAL_API_USER;
import static com.example.security.Authority.INTERNAL_API_USER;

@Configuration
public class AuthenticationConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth.requestMatchers("/external-api/**").hasAuthority(EXTERNAL_API_USER.getAuthority()))
                .httpBasic().authenticationEntryPoint(externalApiEntryPoint());
        http
                .authorizeHttpRequests((auth) -> auth.requestMatchers("/internal-api/**").hasAuthority(INTERNAL_API_USER.getAuthority()))
                .httpBasic().authenticationEntryPoint(internalApiEntryPoint());
        return http.build();
    }

    @Bean
    public AuthenticationEntryPoint externalApiEntryPoint() {
        BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
        entryPoint.setRealmName("EXTERNAL_API_REALM");
        return entryPoint;
    }

    @Bean
    public AuthenticationEntryPoint internalApiEntryPoint() {
        BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
        entryPoint.setRealmName("INTERNAL_API_REALM");
        return entryPoint;
    }
}
