package com.pragma.restaurantcrud.infrastructure.config;

import com.pragma.restaurantcrud.domain.api.IOwnerService;
import com.pragma.restaurantcrud.domain.spi.persistence.ICategoryPersistencePort;
import com.pragma.restaurantcrud.domain.spi.persistence.IDishPersistencePort;
import com.pragma.restaurantcrud.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.restaurantcrud.domain.spi.servicePortClient.IGateway;
import com.pragma.restaurantcrud.domain.usecase.OwnerUsecase;
import com.pragma.restaurantcrud.infrastructure.config.securityClient.JwtProvider;
import com.pragma.restaurantcrud.infrastructure.output.jpa.adapter.DishAdapter;
import com.pragma.restaurantcrud.infrastructure.output.jpa.mapper.IDishEntityMapper;
import com.pragma.restaurantcrud.infrastructure.output.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class DishBeanConfig {
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IGateway gateway;
    private final JwtProvider jwtProvider;

    @Bean
    public IDishPersistencePort dishPersistencePort() {
        return new DishAdapter(dishRepository, dishEntityMapper, categoryPersistencePort, restaurantPersistencePort);
    }

    @Bean
    public IOwnerService ownerService() {
        return new OwnerUsecase(dishPersistencePort(), categoryPersistencePort, restaurantPersistencePort, gateway, jwtProvider);
    }
}
