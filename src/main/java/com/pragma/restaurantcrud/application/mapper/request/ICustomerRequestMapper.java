package com.pragma.restaurantcrud.application.mapper.request;

import com.pragma.restaurantcrud.application.dto.request.CreateOrderRequest;
import com.pragma.restaurantcrud.application.dto.request.DishInOrderRequest;
import com.pragma.restaurantcrud.domain.models.Order;
import com.pragma.restaurantcrud.domain.models.OrderDish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICustomerRequestMapper {

    @Mapping(target="idRestaurant.idRestaurant", source="orderRequest.idRestaurant")
    Order orderRequestToOrder(CreateOrderRequest orderRequest);

    @Mapping(target="idDish.idDish", source="dishInOrderRequest.idDish")
    @Mapping(target="idDish.name", source="dishInOrderRequest.name")
    OrderDish dishInOrderRequestToOrderDish(DishInOrderRequest dishInOrderRequest);
}
