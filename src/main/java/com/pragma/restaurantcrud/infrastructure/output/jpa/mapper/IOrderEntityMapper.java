package com.pragma.restaurantcrud.infrastructure.output.jpa.mapper;

import com.pragma.restaurantcrud.domain.models.Order;
import com.pragma.restaurantcrud.domain.models.OrderDish;
import com.pragma.restaurantcrud.infrastructure.output.jpa.entity.OrderDishEntity;
import com.pragma.restaurantcrud.infrastructure.output.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IOrderEntityMapper {


    @Mapping(target = "employeeRestaurant", source = "idEmployeeRestaurant")
    @Mapping(target = "restaurant", source = "restaurant")
    @Mapping(target = "orderDishEntities", source = "orderDishes")
    OrderEntity mapOrderToOrderEntity(Order order);

    @Mapping(target = "orderEntity.idOrder", expression = "java(orderDish.getOrder().getIdOrder())")
    @Mapping(target = "dishEntity", source = "dish")
    OrderDishEntity mapOrderDishToOrderDishEntity(OrderDish orderDish);


    @Mapping(target = "order.idOrder",  expression = "java(orderDishEntity.getOrderEntity().getIdOrder())")
    @Mapping(target = "dish", source = "dishEntity")
    @Mapping(target = "dish.restaurant", source = "dishEntity.restaurant")
    @Mapping(target = "dish.category", source = "dishEntity.category")
    OrderDish mapOrderDishEntityToOrderDish(OrderDishEntity orderDishEntity);


    @Mapping(target = "idEmployeeRestaurant", source = "employeeRestaurant")
    @Mapping(target = "restaurant", source = "restaurant")
    @Mapping(target = "orderDishes", source = "orderDishEntities")
    Order mapOrderEntityToOrder(OrderEntity orderEntity);
}
