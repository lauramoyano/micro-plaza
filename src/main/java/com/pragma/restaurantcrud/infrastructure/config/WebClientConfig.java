package com.pragma.restaurantcrud.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {

    @Value("${access.url.user}")
    private String urlUsers;
    @Value("${access.url.message}")
    private String urlMessage;

    @Value("${access.url.traceability}")
    private String urlTraceability;

    @Bean("webClientUsers")
    public WebClient webClientUsers() {
        return WebClient.builder()
                .baseUrl(urlUsers)
                .build();
    }


    @Bean("webClientMessage")
    public WebClient messageClient() {
        return WebClient.builder()
                .baseUrl(urlMessage)
                .build();
    }

    @Bean("webClientTraceability")
    public WebClient traceabilityClient() {
        return WebClient.builder()
                .baseUrl(urlTraceability)
                .build();
    }


}
