package com.pragma.restaurantcrud.infrastructure.output.client;

import com.pragma.restaurantcrud.domain.models.User;
import com.pragma.restaurantcrud.domain.spi.servicePortClient.IGateway;
import org.mapstruct.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UserGateway implements IGateway {
    private final WebClient webClient;

    public UserGateway(WebClient webClient) {
        this.webClient = webClient;
    }




    @Override
    public UserDto getUserById(Long id, String token) {
        return webClient.get()
                .uri("/getUserById/{id}", id)
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }

    @Override
    public User getByEmail(String email, String token) {
        return webClient.get()
                .uri("/getByEmail/{email}", email)
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }
}
