package com.pragma.restaurantcrud.domain.api;

import com.pragma.restaurantcrud.domain.models.Dish;

public interface IOwnerService {

    Dish createDish(Dish dish);
    Dish updateDish(Dish dish);
    Dish setDishVisibility(Long idDish, Long idRestaurant, boolean visibility, String token);

}
