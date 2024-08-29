package com.pragma.restaurantcrud.infrastructure.config;

import com.pragma.restaurantcrud.domain.api.IAdminService;
import com.pragma.restaurantcrud.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.restaurantcrud.domain.spi.servicePortClient.IGateway;
import com.pragma.restaurantcrud.domain.usecase.AdminUsecase;
import com.pragma.restaurantcrud.infrastructure.output.jpa.adapter.RestaurantAdapter;
import com.pragma.restaurantcrud.infrastructure.output.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.restaurantcrud.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class RestaurantBeanConfig {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantMapper;
    private final IGateway gateway;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantAdapter(restaurantRepository, restaurantMapper);
    }

    @Bean
    public IAdminService adminService() {
        return new AdminUsecase(restaurantPersistencePort(), gateway);
    }
}
