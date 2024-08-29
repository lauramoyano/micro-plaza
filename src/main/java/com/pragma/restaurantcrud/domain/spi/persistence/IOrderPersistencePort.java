package com.pragma.restaurantcrud.domain.spi.persistence;

import com.pragma.restaurantcrud.domain.models.Order;

import java.util.List;

public interface IOrderPersistencePort {

    Order save(Order order);
    List<Order> findByCustomerId(Long customerId, Long idRestaurant);
    List<Order> findByRestaurantId(Long idRestaurant);
    List<Order> findByRestaurantIdAndCustomerId(Long idRestaurant, Long customerId);
    Order findById(Long idOrder);
    boolean existsById(Long idOrder);

}
