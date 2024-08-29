package com.pragma.restaurantcrud.infrastructure.config;

import com.pragma.restaurantcrud.infrastructure.output.client.IUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {

    @Bean("webClientUsers")
    public WebClient webClientUsers() {
        return WebClient.builder()
                .baseUrl("http://localhost:8089/users")
                .build();
    }


}
