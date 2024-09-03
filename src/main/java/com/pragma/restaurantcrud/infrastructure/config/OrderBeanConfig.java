package com.pragma.restaurantcrud.infrastructure.config;

import com.pragma.restaurantcrud.domain.api.ICustomerService;
import com.pragma.restaurantcrud.domain.spi.persistence.*;
import com.pragma.restaurantcrud.domain.spi.servicePortClient.IGateway;
import com.pragma.restaurantcrud.domain.spi.servicePortClient.IMessage;
import com.pragma.restaurantcrud.domain.usecase.CustomerUsecase;
import com.pragma.restaurantcrud.infrastructure.config.securityClient.JwtProvider;
import com.pragma.restaurantcrud.infrastructure.output.jpa.adapter.OrderAdapter;
import com.pragma.restaurantcrud.infrastructure.output.jpa.adapter.OrderDishAdapter;
import com.pragma.restaurantcrud.infrastructure.output.jpa.mapper.IOrderEntityMapper;
import com.pragma.restaurantcrud.infrastructure.output.jpa.repository.IOrderDishRepository;
import com.pragma.restaurantcrud.infrastructure.output.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class OrderBeanConfig {

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IDishPersistencePort dishPersistencePort;
    private final IOrderRepository orderRepository;
    private final IOrderDishRepository orderDishRepository;
    private final IOrderEntityMapper orderMapper;
    private final IEmployeePersistencePort employeePersistencePort;
    private final IGateway gateway;
    private final JwtProvider jwtProvider;
    private final IMessage messengerService;
    private  final IOrderPersistencePort orderPersistencePort;
    @Bean
    public IOrderDishPersistencePort orderDishPersistencePort() {
        return new OrderDishAdapter(orderDishRepository, orderMapper);
    }
    @Bean
    public ICustomerService customerService() {
        return new CustomerUsecase(dishPersistencePort,restaurantPersistencePort,orderPersistencePort, orderDishPersistencePort(),  messengerService, gateway, jwtProvider);
    }
}