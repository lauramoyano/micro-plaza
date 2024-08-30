package com.pragma.restaurantcrud.infrastructure.output.jpa.mapper;

import com.pragma.restaurantcrud.domain.models.EmployeeRestaurant;
import com.pragma.restaurantcrud.infrastructure.output.jpa.entity.EmployeeRestaurantEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IEmployeeRestaurantMapper {

    EmployeeRestaurant mapEmployeeRestaurantEntityToEmployeeRestaurant(EmployeeRestaurantEntity employeeRestaurantEntity);
    EmployeeRestaurantEntity mapEmployeeRestaurantToEmployeeRestaurantEntity(EmployeeRestaurant employeeRestaurant);
}
