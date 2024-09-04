package com.pragma.restaurantcrud.domainTest;

import com.pragma.restaurantcrud.domain.models.*;
import com.pragma.restaurantcrud.domain.spi.persistence.*;
import com.pragma.restaurantcrud.domain.spi.servicePortClient.IGateway;
import com.pragma.restaurantcrud.domain.spi.servicePortClient.IMessage;
import com.pragma.restaurantcrud.domain.usecase.CustomerUsecase;
import com.pragma.restaurantcrud.infrastructure.config.securityClient.JwtProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerUsecaseTest {

    @InjectMocks
    private CustomerUsecase customerUsecase;

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;



    @Test
    void testFindAllByOrderByNameAscSuccess() {
        List<Restaurant> restaurants = List.of(new Restaurant(), new Restaurant());
        Page<Restaurant> restaurantPage = new PageImpl<>(restaurants);

        when(restaurantPersistencePort.findAllByOrderByNameAsc(PageRequest.of(0, 10))).thenReturn(restaurantPage);

        Page<Restaurant> result = customerUsecase.findAllByOrderByNameAsc(0, 10);

        assertEquals(2, result.getContent().size());
        verify(restaurantPersistencePort).findAllByOrderByNameAsc(PageRequest.of(0, 10));
    }

    @Test
    void testFindAllByOrderByNameAscNoRestaurants() {
        when(restaurantPersistencePort.findAllByOrderByNameAsc(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customerUsecase.findAllByOrderByNameAsc(0, 10);
        });

        assertEquals("There are no restaurants", exception.getMessage());
    }

    @Test
    void testFindAllDishesByRestaurantIdSuccess() {
        Restaurant restaurant = new Restaurant();
        restaurant.setIdRestaurant(1L);
        List<Dish> dishes = List.of(new Dish(), new Dish());
        Page<Dish> dishPage = new PageImpl<>(dishes);

        when(restaurantPersistencePort.findRestaurantById(1L)).thenReturn(restaurant);
        when(dishPersistencePort.findAllDishesByRestaurantId(PageRequest.of(0, 10), 1L)).thenReturn(dishPage);

        Page<Dish> result = customerUsecase.findAllDishesByRestaurantId(1L, 0, 10);

        assertEquals(2, result.getContent().size());
        verify(restaurantPersistencePort).findRestaurantById(1L);
        verify(dishPersistencePort).findAllDishesByRestaurantId(PageRequest.of(0, 10), 1L);
    }

    @Test
    void testFindAllDishesByRestaurantIdRestaurantNotFound() {
        when(restaurantPersistencePort.findRestaurantById(1L)).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customerUsecase.findAllDishesByRestaurantId(1L, 0, 10);
        });

        assertEquals("The restaurant does not exist", exception.getMessage());
    }
}