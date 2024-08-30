package com.pragma.restaurantcrud.infrastructure.output.jpa.repository;

import com.pragma.restaurantcrud.infrastructure.output.jpa.entity.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
   RestaurantEntity findRestaurantByIdRestaurant (Long id);
   boolean existsRestaurantByName(String name);
    boolean existsRestaurantByIdRestaurant(Long id);
    Page<RestaurantEntity> findAllByOrderByNameAsc(Pageable pageable);

}
