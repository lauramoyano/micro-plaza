package com.pragma.restaurantcrud.application.mapper.request;

import com.pragma.restaurantcrud.application.dto.request.CreateDishRequest;
import com.pragma.restaurantcrud.application.dto.request.EmployeeRestaurantRequest;
import com.pragma.restaurantcrud.application.dto.request.UpdateDishRequest;
import com.pragma.restaurantcrud.application.dto.request.VisibilityDishRequest;
import com.pragma.restaurantcrud.domain.models.Category;
import com.pragma.restaurantcrud.domain.models.Dish;
import com.pragma.restaurantcrud.domain.models.EmployeeRestaurant;
import com.pragma.restaurantcrud.domain.models.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface IOwnerRequestMapper {

    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "idDish", ignore = true)
    @Mapping(target = "restaurant", source = "idRestaurant", qualifiedByName = "mapRestaurant")
    @Mapping(target = "category", source = "idCategory", qualifiedByName = "mapCategory")
    Dish createDishRequestDtoToDishModel(CreateDishRequest createDishRequestDto);

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "urlImage", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    Dish updateDishRequestDtoToDishModel(UpdateDishRequest updateDishRequestDto);


    @Mapping(target = "name", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "urlImage", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    Dish visibilityDishRequestDtoToDishModel(VisibilityDishRequest visibilityDishRequestDto);

    @Named("mapCategory")
    default Category mapCategory(Long idCategory) {
        if (idCategory == null) {
            return null;
        }
        Category category = new Category();
        category.setIdCategory(idCategory);
        return category;
    }

    @Named("mapRestaurant")
    default Restaurant mapRestaurant(Long idRestaurant) {
        if (idRestaurant == null) {
            return null;
        }
        Restaurant restaurant = new Restaurant();
        restaurant.setIdRestaurant(idRestaurant);
        return restaurant;
    }


}