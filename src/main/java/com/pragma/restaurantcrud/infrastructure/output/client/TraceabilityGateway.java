package com.pragma.restaurantcrud.infrastructure.output.client;

import com.pragma.restaurantcrud.domain.models.TraceabilityModel;
import com.pragma.restaurantcrud.domain.spi.servicePortClient.ITraceability;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Qualifier;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class TraceabilityGateway implements ITraceability {
    private final WebClient webClient;
    private final ITraceabilityMapper traceabilityMapper;

    public TraceabilityGateway(@Qualifier("webClientTraceability") WebClient webClient, ITraceabilityMapper traceabilityMapper) {
        this.webClient = webClient;
        this.traceabilityMapper = traceabilityMapper;
    }

    @Override
    public void saveTraceability(TraceabilityModel traceabilityModel) {
        OrderTraceabilityRequestDto orderTraceabilityRequestDto = this.traceabilityMapper.mapToOrderTraceabilityRequestDto(traceabilityModel);
        this.webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/save").build())
                .bodyValue(orderTraceabilityRequestDto)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()) {
                        return clientResponse.bodyToMono(OrderTraceabilityRequestDto.class);
                    } else {
                        return Mono.error(new Exception("Error with the request traceability: " + clientResponse.statusCode()));
                    }
                })
                .doOnError(e -> {
                    // Log de errores para facilitar la depuración
                    System.err.println("Error saving traceability: " + e.getMessage());
                })
                .block();
    }

    @Override
    public TraceabilityModel getTraceability(Long idOrder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TraceabilityModel> getAllTraceability(Integer idOrder) {
        return webClient.get()
                .uri("/findAll/{orderId}", idOrder)
                .retrieve()
                .bodyToFlux(OrderTraceabilityRequestDto.class)
                .map(traceabilityMapper::mapToTraceabilityModel) // Usar el método de mapeo inverso correcto
                .collectList()
                .block();
    }
}
