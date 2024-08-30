package com.pragma.restaurantcrud.infrastructure.config.securityClient;

import org.mapstruct.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

@Service
public class TokenUser implements  ITokenUser{

    private final WebClient webClient;

    public TokenUser(WebClient webClient) {
        this.webClient = webClient;
    }
    @Override
    public boolean isValidToken(String token) {

        try {
            this.webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/validateToken")
                            .build())
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .exchangeToMono(clientResponse -> {
                        if (clientResponse.statusCode().equals(HttpStatus.NO_CONTENT)){
                            return Mono.empty();
                        } else {
                            return clientResponse.createException().flatMap(Mono::error);
                        }
                    }).block();

                  return true;}
        catch (Exception e) {
            return false;
        }
    }

}
