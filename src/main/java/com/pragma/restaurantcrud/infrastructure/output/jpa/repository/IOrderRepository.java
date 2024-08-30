package com.pragma.restaurantcrud.infrastructure.output.jpa.repository;

import com.pragma.restaurantcrud.infrastructure.output.jpa.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

        List<OrderEntity> findAllByIdCustomerAndRestaurantIdRestaurant(Long idCustomer, Long idRestaurant);

        List<OrderEntity> findAllByRestaurantIdRestaurantAndStatus(Long idRestaurant, String status);

        Page<OrderEntity> findAllByRestaurantIdRestaurantAndStatus(Long idRestaurant, String status, Pageable pageable);
}
