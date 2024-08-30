package com.pragma.restaurantcrud.infrastructure.output.jpa.mapper;

import com.pragma.restaurantcrud.domain.models.Order;
import com.pragma.restaurantcrud.domain.models.OrderDish;
import com.pragma.restaurantcrud.infrastructure.output.jpa.entity.OrderDishEntity;
import com.pragma.restaurantcrud.infrastructure.output.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IOrderEntityMapper {



    OrderEntity mapOrderToOrderEntity(Order order);



    OrderDishEntity mapOrderDishToOrderDishEntity(OrderDish orderDish);


    OrderDish mapOrderDishEntityToOrderDish(OrderDishEntity orderDishEntity);


    Order mapOrderEntityToOrder(OrderEntity orderEntity);
}

