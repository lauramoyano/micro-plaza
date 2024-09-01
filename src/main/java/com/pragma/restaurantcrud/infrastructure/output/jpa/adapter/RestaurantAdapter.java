package com.pragma.restaurantcrud.infrastructure.output.jpa.adapter;


import com.pragma.restaurantcrud.domain.models.Restaurant;
import com.pragma.restaurantcrud.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.restaurantcrud.infrastructure.output.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.restaurantcrud.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class RestaurantAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantMapper;

    @Override
    public boolean existsRestaurantByName(String name) {
        return restaurantRepository.existsRestaurantByName(name);
    }
    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
       return restaurantMapper.restaurantEntityToRestaurant((restaurantRepository.save(restaurantMapper.restaurantToRestaurantEntity(restaurant))));
    }

    @Override
    public Restaurant findRestaurantById(Long id) {
        return restaurantMapper.restaurantEntityToRestaurant(restaurantRepository.findRestaurantByIdRestaurant(id));
    }

    @Override
    public boolean existsRestaurantById(Long id) {
        return restaurantRepository.existsRestaurantByIdRestaurant(id);
    }

    @Override
    public Page<Restaurant>  findAllByOrderByNameAsc(Pageable pageable) {
        return restaurantRepository.findAllByOrderByNameAsc(pageable).map(restaurantMapper::restaurantEntityToRestaurant);
    }

    @Override
    public Restaurant findByIdOwner(Long idOwner) {
        return restaurantMapper.restaurantEntityToRestaurant(restaurantRepository.findByIdOwner(idOwner));
    }


}
