package com.pragma.restaurantcrud.application.handler;

import com.pragma.restaurantcrud.application.dto.request.CreateOrderRequest;
import com.pragma.restaurantcrud.application.dto.response.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICustomerServiceHandler {

    Page<RestaurantPageResponse>  getAllRestaurants(Integer page, Integer size);
    Page<DishesPageResponse> getAllDishes(Integer page, Integer size, Long idRestaurant);
    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest, String token);
    OrderNotifyResponse cancelOrder(Long idOrder, String token);
    List<OrderTraceabilityResponseDto> orderTraceability(Long idOrder, String token);

}
