package com.pragma.restaurantcrud.infrastructure.output.jpa.repository;

import com.pragma.restaurantcrud.infrastructure.output.jpa.entity.DishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDishRepository extends JpaRepository<DishEntity, Long> {

    DishEntity findDishByIdDish(Long idDish);
    boolean existsDishByIdDish(Long idDish);
    Page<DishEntity> findDishEntitiesBy(Pageable pageable, Long idRestaurant);

}
