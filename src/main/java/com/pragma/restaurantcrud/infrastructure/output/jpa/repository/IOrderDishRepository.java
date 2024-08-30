package com.pragma.restaurantcrud.infrastructure.output.jpa.repository;

import com.pragma.restaurantcrud.infrastructure.output.jpa.entity.OrderDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDishRepository extends JpaRepository<OrderDishEntity, Long> {



}
