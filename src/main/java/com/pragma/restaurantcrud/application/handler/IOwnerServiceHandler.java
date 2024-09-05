package com.pragma.restaurantcrud.application.handler;

import com.pragma.restaurantcrud.application.dto.request.CreateDishRequest;
import com.pragma.restaurantcrud.application.dto.request.EmployeeRestaurantRequest;
import com.pragma.restaurantcrud.application.dto.request.UpdateDishRequest;
import com.pragma.restaurantcrud.application.dto.response.*;
import com.pragma.restaurantcrud.application.dto.response.dto.OrderDto;
import com.pragma.restaurantcrud.application.dto.response.dto.RestaurantEfficiencyResponseDto;

import java.util.List;

public interface IOwnerServiceHandler {
    CreateDishResponse createDish(CreateDishRequest createDishRequest, String token);
    UpdateDishResponse updateDish(UpdateDishRequest updateDishRequest, String token);
    UpdateDishResponse setDishVisibility(Long idDish, Long idRestaurant, boolean visibility, String token);
    EmployeeRestaurantResponse saveUserEmployeeInTheRestaurant(EmployeeRestaurantRequest employeeRestaurantRequest, String token);
    RestaurantEfficiencyResponseDto getRestaurantEfficiency(List<OrderDto> orderModelList, String token);
}
