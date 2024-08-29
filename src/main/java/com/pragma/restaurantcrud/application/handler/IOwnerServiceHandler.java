package com.pragma.restaurantcrud.application.handler;

import com.pragma.restaurantcrud.application.dto.request.CreateDishRequest;
import com.pragma.restaurantcrud.application.dto.request.UpdateDishRequest;
import com.pragma.restaurantcrud.application.dto.request.VisibilityDishRequest;
import com.pragma.restaurantcrud.application.dto.response.CreateDishResponse;
import com.pragma.restaurantcrud.application.dto.response.UpdateDishResponse;

public interface IOwnerServiceHandler {
    CreateDishResponse createDish(CreateDishRequest createDishRequest);
    UpdateDishResponse updateDish(UpdateDishRequest updateDishRequest);
    UpdateDishResponse setDishVisibility(Long idDish, Long idRestaurant, boolean visibility, String token);
}
