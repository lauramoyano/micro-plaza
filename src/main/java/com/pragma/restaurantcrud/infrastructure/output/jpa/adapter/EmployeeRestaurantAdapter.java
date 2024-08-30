package com.pragma.restaurantcrud.infrastructure.output.jpa.adapter;

import com.pragma.restaurantcrud.domain.models.EmployeeRestaurant;
import com.pragma.restaurantcrud.domain.spi.persistence.IEmployeePersistencePort;
import com.pragma.restaurantcrud.infrastructure.output.jpa.mapper.IEmployeeRestaurantMapper;
import com.pragma.restaurantcrud.infrastructure.output.jpa.repository.IEmployeeRestaurantRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeeRestaurantAdapter implements IEmployeePersistencePort {
    private final IEmployeeRestaurantRepository employeeRepository;
    private final IEmployeeRestaurantMapper employeeMapper;

    @Override
    public EmployeeRestaurant save(EmployeeRestaurant employee) {
        return employeeMapper.mapEmployeeRestaurantEntityToEmployeeRestaurant(employeeRepository.save(employeeMapper.mapEmployeeRestaurantToEmployeeRestaurantEntity(employee)));
    }

    @Override
    public EmployeeRestaurant findById(Long idEmployeeRestaurant) {
        return employeeMapper.mapEmployeeRestaurantEntityToEmployeeRestaurant(employeeRepository.findByIdEmployee(idEmployeeRestaurant));
    }
}
