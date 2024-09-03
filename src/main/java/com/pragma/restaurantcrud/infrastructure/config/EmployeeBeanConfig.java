package com.pragma.restaurantcrud.infrastructure.config;

import com.pragma.restaurantcrud.domain.api.IEmployeeService;
import com.pragma.restaurantcrud.domain.spi.persistence.IEmployeePersistencePort;
import com.pragma.restaurantcrud.domain.spi.persistence.IOrderPersistencePort;
import com.pragma.restaurantcrud.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.restaurantcrud.domain.spi.servicePortClient.IGateway;
import com.pragma.restaurantcrud.domain.spi.servicePortClient.IMessage;
import com.pragma.restaurantcrud.domain.usecase.EmployeeUsecase;
import com.pragma.restaurantcrud.infrastructure.config.securityClient.JwtProvider;
import com.pragma.restaurantcrud.infrastructure.output.jpa.adapter.EmployeeRestaurantAdapter;
import com.pragma.restaurantcrud.infrastructure.output.jpa.mapper.IEmployeeRestaurantMapper;
import com.pragma.restaurantcrud.infrastructure.output.jpa.mapper.IOrderEntityMapper;
import com.pragma.restaurantcrud.infrastructure.output.jpa.repository.IEmployeeRestaurantRepository;
import com.pragma.restaurantcrud.infrastructure.output.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class EmployeeBeanConfig {

    private final IGateway gateway;
    private final JwtProvider jwtProvider;
    private final IEmployeePersistencePort employeePersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IOrderPersistencePort orderPersistencePort;
    private final IMessage messengerService;

    @Bean
    public IEmployeeService employeeService() {
        return new EmployeeUsecase(employeePersistencePort, restaurantPersistencePort, orderPersistencePort, gateway, jwtProvider, messengerService);
    }
}
