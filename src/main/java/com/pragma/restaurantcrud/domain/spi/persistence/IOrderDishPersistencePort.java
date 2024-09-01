package com.pragma.restaurantcrud.domain.spi.persistence;

import com.pragma.restaurantcrud.domain.models.OrderDish;

import java.util.List;

public interface IOrderDishPersistencePort {

    OrderDish save(OrderDish orderDish);
    List<OrderDish> saveAll(List<OrderDish> orderDishes);


}
