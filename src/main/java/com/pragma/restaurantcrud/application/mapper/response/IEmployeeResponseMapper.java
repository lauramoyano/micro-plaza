package com.pragma.restaurantcrud.application.mapper.response;


import com.pragma.restaurantcrud.application.dto.response.DishInOrderResponse;
import com.pragma.restaurantcrud.application.dto.response.EmployeeRestaurantResponse;
import com.pragma.restaurantcrud.application.dto.response.OrdersPageResponse;
import com.pragma.restaurantcrud.domain.models.EmployeeRestaurant;
import com.pragma.restaurantcrud.domain.models.Order;
import com.pragma.restaurantcrud.domain.models.OrderDish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IEmployeeResponseMapper {

    @Mapping(target = "dishes.", source = "orderDishes", qualifiedByName =  "mapDishes")
    @Mapping(target = "idRestaurant", source = "restaurant.idRestaurant")
    OrdersPageResponse toOrder(Order order);

    EmployeeRestaurantResponse employeeRestaurantToEmployeeRestaurantResponse(EmployeeRestaurant employeeRestaurant);

    @Named("mapDishes")
    default List<DishInOrderResponse> mapDishes(List<OrderDish> orderDishes) {
        return orderDishes.stream()
                .map(orderDish -> new DishInOrderResponse(
                        orderDish.getDish().getIdDish(), // Suponiendo que getIdDish() retorna el ID del plato
                        orderDish.getQuantity()
                ))
                .collect(Collectors.toList());
    }
}
