package com.pragma.restaurantcrud.domain.spi.persistence;

import com.pragma.restaurantcrud.domain.models.Restaurant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface IRestaurantPersistencePort {

    Restaurant saveRestaurant(Restaurant restaurant);
    Page<Restaurant> findAllRestaurants(Pageable pageable);
    Restaurant findRestaurantById(Long idRestaurant);
    Restaurant updateRestaurant(Restaurant restaurant);
    boolean existsRestaurantById(Long idRestaurant);
    boolean existsRestaurantByName(String name);

}
