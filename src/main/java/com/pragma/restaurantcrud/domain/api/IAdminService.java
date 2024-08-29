package com.pragma.restaurantcrud.domain.api;

import com.pragma.restaurantcrud.domain.models.Dish;
import com.pragma.restaurantcrud.domain.models.Restaurant;

public interface IAdminService {

    Restaurant createRestaurant(Restaurant restaurant, String token);
    boolean existsRestaurantByName(String name);
}
