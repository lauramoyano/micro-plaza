package com.pragma.restaurantcrud.infrastructure.output.jpa.adapter;

import com.pragma.restaurantcrud.domain.models.EmployeeRestaurant;
import com.pragma.restaurantcrud.domain.models.Order;
import com.pragma.restaurantcrud.domain.spi.persistence.IEmployeePersistencePort;
import com.pragma.restaurantcrud.infrastructure.output.jpa.mapper.IEmployeeRestaurantMapper;
import com.pragma.restaurantcrud.infrastructure.output.jpa.mapper.IOrderEntityMapper;
import com.pragma.restaurantcrud.infrastructure.output.jpa.repository.IEmployeeRestaurantRepository;
import com.pragma.restaurantcrud.infrastructure.output.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class EmployeeRestaurantAdapter implements IEmployeePersistencePort {
    private final IEmployeeRestaurantRepository employeeRepository;
    private final IEmployeeRestaurantMapper employeeMapper;
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper order;

    @Override
    public EmployeeRestaurant save(EmployeeRestaurant employee) {
        return employeeMapper.mapEmployeeRestaurantEntityToEmployeeRestaurant(employeeRepository.save(employeeMapper.mapEmployeeRestaurantToEmployeeRestaurantEntity(employee)));
    }

    @Override
    public EmployeeRestaurant findById(Long idEmployeeRestaurant) {
        return employeeMapper.mapEmployeeRestaurantEntityToEmployeeRestaurant(employeeRepository.findByIdEmployee(idEmployeeRestaurant));
    }

    @Override
    public Long findIdRestaurantByIdEmployee(Long idEmployee) {
        return employeeRepository.findIdRestaurantByIdEmployee(idEmployee);
    }



}
