package com.pragma.restaurantcrud.infrastructure.output.jpa.mapper;

import com.pragma.restaurantcrud.domain.models.Restaurant;
import com.pragma.restaurantcrud.infrastructure.output.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRestaurantEntityMapper {


    RestaurantEntity restaurantToRestaurantEntity(Restaurant restaurant);
    Restaurant restaurantEntityToRestaurant(RestaurantEntity restaurantEntity);
}
