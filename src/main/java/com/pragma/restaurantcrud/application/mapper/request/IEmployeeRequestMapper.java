package com.pragma.restaurantcrud.application.mapper.request;

import com.pragma.restaurantcrud.application.dto.request.EmployeeRestaurantRequest;
import com.pragma.restaurantcrud.domain.models.EmployeeRestaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IEmployeeRequestMapper {

    EmployeeRestaurant employeeRestaurantRequestDtoToEmployeeRestaurantModel(EmployeeRestaurantRequest employeeRestaurantRequestDto);

}
