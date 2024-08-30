package com.pragma.restaurantcrud.infrastructure.output.jpa.adapter;

import com.pragma.restaurantcrud.domain.models.Dish;
import com.pragma.restaurantcrud.domain.spi.persistence.ICategoryPersistencePort;
import com.pragma.restaurantcrud.domain.spi.persistence.IDishPersistencePort;
import com.pragma.restaurantcrud.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.restaurantcrud.infrastructure.output.jpa.mapper.IDishEntityMapper;
import com.pragma.restaurantcrud.infrastructure.output.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DishAdapter implements IDishPersistencePort {
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishMapper;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;

    @Override
    public Dish save(Dish dish) {
        return dishMapper.mapDishEntityToDish(dishRepository.save(dishMapper.mapDishToDishEntity(dish)));
    }

    @Override
    public Dish findByIdDish(Long id) {
        return dishMapper.mapDishEntityToDish(dishRepository.findDishByIdDish(id));
    }

    @Override
    public Page<Dish>  findAllDishesByRestaurantId(Pageable pageable, Long idRestaurant) {
        return dishRepository.findByRestaurantIdRestaurantOrderByCategoryNameAsc(pageable, idRestaurant)
                .filter(dish -> dish.getEnabled().equals(true)).map(this.dishMapper::mapDishEntityToDish)
                .stream().collect(Collectors.collectingAndThen(Collectors.toList(), PageImpl::new));
    }
}