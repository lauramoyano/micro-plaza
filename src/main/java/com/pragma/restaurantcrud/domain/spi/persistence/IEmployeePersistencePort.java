package com.pragma.restaurantcrud.domain.spi.persistence;

import com.pragma.restaurantcrud.domain.models.EmployeeRestaurant;


public interface IEmployeePersistencePort {

    EmployeeRestaurant save(EmployeeRestaurant employee);

    EmployeeRestaurant findById(Long idEmployeeRestaurant);

    Long findIdRestaurantByIdEmployee(Long idEmployee);
}