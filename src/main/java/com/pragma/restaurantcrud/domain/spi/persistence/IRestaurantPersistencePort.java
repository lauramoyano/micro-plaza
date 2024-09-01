package com.pragma.restaurantcrud.domain.spi.persistence;

import com.pragma.restaurantcrud.domain.models.Restaurant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface IRestaurantPersistencePort {

    Restaurant saveRestaurant(Restaurant restaurant);
    Page<Restaurant>  findAllByOrderByNameAsc(Pageable pageable);
    Restaurant findRestaurantById(Long idRestaurant);
    boolean existsRestaurantById(Long idRestaurant);
    boolean existsRestaurantByName(String name);
    Restaurant findByIdOwner(Long idOwner);

}
