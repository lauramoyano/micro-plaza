package com.pragma.restaurantcrud.application.handler;

import com.pragma.restaurantcrud.application.dto.request.CreateOrderRequest;
import com.pragma.restaurantcrud.application.dto.response.CreateOrderResponse;
import com.pragma.restaurantcrud.application.dto.response.DishesPageResponse;
import com.pragma.restaurantcrud.application.dto.response.RestaurantPageResponse;
import org.springframework.data.domain.Page;

public interface ICustomerServiceHandler {

    Page<RestaurantPageResponse>  getAllRestaurants(Integer page, Integer size);
    Page<DishesPageResponse> getAllDishes(Integer page, Integer size, Long idRestaurant);
    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest, String token);
}
