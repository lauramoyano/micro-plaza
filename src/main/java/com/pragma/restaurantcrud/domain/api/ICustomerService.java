package com.pragma.restaurantcrud.domain.api;

import com.pragma.restaurantcrud.domain.models.Dish;
import com.pragma.restaurantcrud.domain.models.Order;
import com.pragma.restaurantcrud.domain.models.Restaurant;
import org.springframework.data.domain.Page;

public interface ICustomerService {

    Page<Restaurant> findAllByOrderByNameAsc(Integer page, Integer size);
    Page<Dish> findAllDishesByRestaurantId(Long restaurantId, Integer page, Integer size);
    Order createOrder(Order order, String token);

}
