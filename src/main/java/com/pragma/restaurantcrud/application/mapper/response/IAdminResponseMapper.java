package com.pragma.restaurantcrud.application.mapper.response;

import com.pragma.restaurantcrud.application.dto.response.CreateRestaurantResponse;
import com.pragma.restaurantcrud.domain.models.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAdminResponseMapper {
    CreateRestaurantResponse restaurantToCreateRestaurantResponse(Restaurant restaurant);
}
