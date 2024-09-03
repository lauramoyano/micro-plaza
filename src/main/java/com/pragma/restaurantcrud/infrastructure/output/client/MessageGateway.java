package com.pragma.restaurantcrud.infrastructure.output.client;

import com.pragma.restaurantcrud.domain.models.PinMessage;
import com.pragma.restaurantcrud.domain.spi.servicePortClient.IMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MessageGateway implements IMessage {

    private final WebClient webClient;
    private final IMessageMapper messageMapper;

    public MessageGateway(@Qualifier("webClientMessage") WebClient webClient, IMessageMapper messageMapper) {
        this.webClient = webClient;
        this.messageMapper = messageMapper;
    }

    @Override
    public void sendNotification(PinMessage pinMessage, String token) {
        MessageRequestDto messageRequestDto = this.messageMapper.messageSmsToMessageSmsRequestDto(pinMessage);
        final String withoutBearer = token.replace("Bearer ", "");

        this.webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/send").build())
                .header(HttpHeaders.AUTHORIZATION, withoutBearer)
                .bodyValue(messageRequestDto)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.CREATED)) {
                        return clientResponse.bodyToMono(MessageResponseDto.class);
                    } else {
                        return Mono.error(new Exception("Error with the request message: " + clientResponse.statusCode()));
                    }
                })
                .doOnError(e -> {
                    // Log de errores para facilitar la depuración
                    System.err.println("Error sending notification: " + e.getMessage());
                })
                .block();
    }
}
