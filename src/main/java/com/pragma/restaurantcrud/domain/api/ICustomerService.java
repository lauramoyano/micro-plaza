package com.pragma.restaurantcrud.domain.api;

import com.pragma.restaurantcrud.domain.models.Restaurant;
import org.springframework.data.domain.Page;

public interface ICustomerService {

    Page<Restaurant> getRestaurants(Integer page, Integer size);
    Restaurant getRestaurantById(Long id);

}
