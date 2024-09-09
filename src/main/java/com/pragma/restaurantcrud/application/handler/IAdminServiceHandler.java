package com.pragma.restaurantcrud.application.handler;

import com.pragma.restaurantcrud.application.dto.request.CreateRestaurantDto;
import com.pragma.restaurantcrud.application.dto.response.CreateRestaurantResponse;

public interface IAdminServiceHandler {
    CreateRestaurantResponse createRestaurant(CreateRestaurantDto createRestaurantDto, String token) ;

}
