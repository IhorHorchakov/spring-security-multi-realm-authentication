package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.security.Authority.EXTERNAL_API_USER;
import static com.example.security.Authority.INTERNAL_API_USER;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User
                .withUsername("johndoe@gmail.com")
                .password(passwordEncoder().encode("johndoe"))
                .authorities(EXTERNAL_API_USER)
                .build());
        manager.createUser(User
                .withUsername("fairyprincess@gmail.com")
                .password(passwordEncoder().encode("fairyprincess"))
                .authorities(INTERNAL_API_USER)
                .build());
        return manager;
    }

    /**
     * The implementation of AuthenticationManager that uses UserCredentials (login, password) to authenticate a user
     * by leveraging DaoAuthenticationProvider.
     */
    @Bean
    public AuthenticationManager userCredentialsAuthenticationManager() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authProvider);
    }
}
