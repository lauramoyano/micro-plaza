package com.pragma.restaurantcrud.domain.spi.persistence;

import com.pragma.restaurantcrud.domain.models.Order;

import java.util.List;

public interface IOrderPersistencePort {

    Order save(Order order);
    List<Order> findByRestaurantIdAndCustomerId(Long idRestaurant, Long customerId);
    boolean existsById(Long idOrder);

}
