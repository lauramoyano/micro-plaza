package com.pragma.restaurantcrud.infrastructure.config;

import com.pragma.restaurantcrud.domain.spi.persistence.IEmployeePersistencePort;
import com.pragma.restaurantcrud.domain.spi.persistence.IOrderPersistencePort;
import com.pragma.restaurantcrud.infrastructure.output.jpa.adapter.EmployeeRestaurantAdapter;
import com.pragma.restaurantcrud.infrastructure.output.jpa.adapter.OrderAdapter;
import com.pragma.restaurantcrud.infrastructure.output.jpa.mapper.IEmployeeRestaurantMapper;
import com.pragma.restaurantcrud.infrastructure.output.jpa.mapper.IOrderEntityMapper;
import com.pragma.restaurantcrud.infrastructure.output.jpa.repository.IEmployeeRestaurantRepository;
import com.pragma.restaurantcrud.infrastructure.output.jpa.repository.IOrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonBean {
    @Bean
    public IOrderPersistencePort orderPersistencePort(IOrderRepository orderRepository, IOrderEntityMapper orderMapper) {
        return new OrderAdapter(orderRepository, orderMapper);
    }

    @Bean
    public IEmployeePersistencePort employeePersistencePort(IEmployeeRestaurantRepository employeeRepository, IEmployeeRestaurantMapper employeeMapper, IOrderRepository orderRepository, IOrderEntityMapper order) {
        return new EmployeeRestaurantAdapter(employeeRepository, employeeMapper, orderRepository, order);
    }

}
