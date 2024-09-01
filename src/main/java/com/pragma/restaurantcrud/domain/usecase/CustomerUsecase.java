package com.pragma.restaurantcrud.domain.usecase;

import com.pragma.restaurantcrud.domain.api.ICustomerService;
import com.pragma.restaurantcrud.domain.models.*;
import com.pragma.restaurantcrud.domain.spi.persistence.*;
import com.pragma.restaurantcrud.domain.spi.servicePortClient.IGateway;
import com.pragma.restaurantcrud.infrastructure.config.securityClient.JwtProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerUsecase  implements ICustomerService {
    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IGateway gateway;
    private final JwtProvider jwtProvider;
    private final IOrderPersistencePort orderPersistencePort;
    private final IOrderDishPersistencePort orderDishPersistencePort;

    public CustomerUsecase(IDishPersistencePort dishPersistencePort
            , IRestaurantPersistencePort restaurantPersistencePort, IOrderPersistencePort orderPersistencePort,IOrderDishPersistencePort iOrderDishPersistencePort  ,IGateway gateway,
                           JwtProvider jwtProvider) {
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.orderPersistencePort = orderPersistencePort;
        this.orderDishPersistencePort = iOrderDishPersistencePort;
        this.gateway = gateway;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public Page<Restaurant> findAllByOrderByNameAsc (Integer page, Integer size) {
        Page<Restaurant> restaurants = this.restaurantPersistencePort.findAllByOrderByNameAsc(PageRequest.of(page, size));
        if(restaurants.isEmpty())
            throw new IllegalArgumentException("There are no restaurants");
        return restaurants;
    }

    @Override
    public Page<Dish> findAllDishesByRestaurantId(Long restaurantId, Integer page, Integer size) {
        Restaurant restaurant = this.restaurantPersistencePort.findRestaurantById(restaurantId);
        if(restaurant == null)
            throw new IllegalArgumentException("The restaurant does not exist");

        Page<Dish> dishes = this.dishPersistencePort.findAllDishesByRestaurantId(PageRequest.of(page, size), restaurantId);
        if(dishes.isEmpty())
            throw new IllegalArgumentException("There are no dishes");
        return dishes;
    }

    @Override
    public Order createOrder(Order order, String token) {
        String email = jwtProvider.getAuthentication(token.replace("Bearer ", "").trim()).getName();
        User user = this.gateway.getUserByEmail(email, token);

        Restaurant restaurant = this.restaurantPersistencePort.findRestaurantById(order.getRestaurant().getIdRestaurant());
        if (restaurant == null) {
            throw new IllegalArgumentException("The restaurant does not exist");
        }

        Long idRestaurant = restaurant.getIdRestaurant();
        Long idCustomer = user.getId();

        long ordersInProcess = this.orderPersistencePort.findByRestaurantIdAndCustomerId(idRestaurant, idCustomer).stream()
                .filter(o -> !o.getStatus().equals("CANCELADO") && !o.getStatus().equals("ENTREGADO")).count();

        if (ordersInProcess > 0) {
            throw new IllegalArgumentException("You have an order in process");
        }
        List<OrderDish> ordersDishes = order.getOrderDishes();

        order.setIdCustomer(idCustomer);
        order.setDate(new Date());
        order.setStatus("PENDIENTE");
        order.setOrderDishes(new ArrayList<>());
        // Guarda el objeto Order
        Order orderModelSaved = this.orderPersistencePort.save(order);

        orderModelSaved.setOrderDishes(ordersDishes);

        // Asigna manualmente el Order a cada OrderDish después de guardar el Order
        ordersDishes.forEach(orderDish -> orderDish.setOrder(orderModelSaved));


        // Persiste las relaciones de OrderDish
        List<OrderDish> orderDishModelsSaved = this.orderDishPersistencePort.saveAll(ordersDishes);
        orderModelSaved.setOrderDishes(orderDishModelsSaved);

        return orderModelSaved;
    }


}
