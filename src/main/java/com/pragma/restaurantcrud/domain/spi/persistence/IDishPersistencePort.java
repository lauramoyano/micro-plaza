package com.pragma.restaurantcrud.domain.spi.persistence;

import com.pragma.restaurantcrud.domain.models.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDishPersistencePort {
    Dish save(Dish dish);
    Dish findByIdDish(Long idDish);
    Page<Dish>  findAllDishesByRestaurantId(Pageable pageable, Long idRestaurant);
}
