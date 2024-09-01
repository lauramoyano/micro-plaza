package com.pragma.restaurantcrud.application.mapper.response;

import com.pragma.restaurantcrud.application.dto.response.CreateDishResponse;
import com.pragma.restaurantcrud.application.dto.response.EmployeeRestaurantResponse;
import com.pragma.restaurantcrud.application.dto.response.UpdateDishResponse;
import com.pragma.restaurantcrud.domain.models.Dish;
import com.pragma.restaurantcrud.domain.models.EmployeeRestaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IOwnerResponseMapper {

    CreateDishResponse dishToCreateDishResponse(Dish dish);

    UpdateDishResponse dishToUpdateDishResponse(Dish dish);

}
