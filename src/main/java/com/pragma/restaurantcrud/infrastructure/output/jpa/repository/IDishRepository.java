package com.pragma.restaurantcrud.infrastructure.output.jpa.repository;

import com.pragma.restaurantcrud.infrastructure.output.jpa.entity.DishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDishRepository extends JpaRepository<DishEntity, Long> {

    DishEntity findDishByIdDish(Long idDish);
    boolean existsDishByIdDish(Long idDish);
    Page<DishEntity>  findByRestaurantIdRestaurantOrderByCategoryNameAsc(Pageable pageable, Long idRestaurant);




}
