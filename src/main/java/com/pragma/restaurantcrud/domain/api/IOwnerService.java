package com.pragma.restaurantcrud.domain.api;

import com.pragma.restaurantcrud.application.dto.response.dto.RestaurantEfficiencyResponseDto;
import com.pragma.restaurantcrud.domain.models.Dish;
import com.pragma.restaurantcrud.domain.models.Order;

import java.util.List;

public interface IOwnerService {

    Dish createDish(Dish dish, String token);
    Dish updateDish(Dish dish, String token);
    Dish setDishVisibility(Long idDish, Long idRestaurant, boolean visibility, String token);
    RestaurantEfficiencyResponseDto getRestaurantEfficiency(List<Order> orderModelList, String token);

}
