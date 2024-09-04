package com.pragma.restaurantcrud.domain.api;

import com.pragma.restaurantcrud.domain.models.Dish;

public interface IOwnerService {

    Dish createDish(Dish dish, String token);
    Dish updateDish(Dish dish, String token);
    Dish setDishVisibility(Long idDish, Long idRestaurant, boolean visibility, String token);

}
