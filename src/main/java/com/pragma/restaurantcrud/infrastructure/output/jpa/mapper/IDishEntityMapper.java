package com.pragma.restaurantcrud.infrastructure.output.jpa.mapper;

import com.pragma.restaurantcrud.domain.models.Dish;
import com.pragma.restaurantcrud.infrastructure.output.jpa.entity.DishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IDishEntityMapper {



    DishEntity mapDishToDishEntity(Dish dish);


    Dish mapDishEntityToDish(DishEntity dishEntity);
}
