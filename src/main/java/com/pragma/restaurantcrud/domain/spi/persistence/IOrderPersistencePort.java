package com.pragma.restaurantcrud.domain.spi.persistence;

import com.pragma.restaurantcrud.domain.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderPersistencePort {

    Order save(Order order);
    List<Order> findByRestaurantIdAndCustomerId(Long idRestaurant, Long customerId);

    boolean existsById(Long idOrder);
    Page<Order> findAllOrdersByStatusAndSizeItemsByPage(Pageable page, Long idEmployeeRestaurant, String status);

    Order findById(Long idOrder);

}
