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
        if (!jwtProvider.validateToken(token))
            throw new IllegalArgumentException("Invalid token");
        final String email = jwtProvider.getEmailFromToken(token);
        final User user = this.gateway.getUserByEmail(email, token);
        final Restaurant restaurant = this.restaurantPersistencePort.findRestaurantById(order.getIdRestaurant().getIdRestaurant());
        if (restaurant == null)
            throw new IllegalArgumentException("The restaurant does not exist");
        Long idRestaurant = restaurant.getIdRestaurant();
        Long idCustomer = user.getId();
        final long ordesInprocess = this.orderPersistencePort.findByRestaurantIdAndCustomerId(idRestaurant, idCustomer).stream()
                .filter(order1 -> !order1.getStatus().equals("CANCELADO") && !order1.getStatus().equals("ENTREGADO")).count();

        if (ordesInprocess > 0)
            throw new IllegalArgumentException("You have an order in process");

        List<OrderDish> orderDishes = order.getOrderDishes();
        order.setDate(new Date());
        order.setIdCustomer(idCustomer);
        order.setStatus("PENDIENTE");
        order.setOrderDishes(new ArrayList<>());
        Order orderCreated = this.orderPersistencePort.save(order);

        orderCreated.setOrderDishes(orderDishes);
        List<OrderDish> orderDishesCreated = createOrderDishes(orderCreated);

        List<OrderDish> orderDishesSaved = this.orderDishPersistencePort.saveAll(orderDishesCreated);
        orderCreated.setOrderDishes(orderDishesSaved);
        return orderCreated;
    }

        private List<OrderDish> createOrderDishes(Order order) {
            List<OrderDish> orderDishes = new ArrayList<>();
            for (OrderDish orderDish : order.getOrderDishes()) {
                Dish dish = this.dishPersistencePort.findByIdDish(orderDish.getIdDish().getIdDish());
                if (dish == null)
                    throw new IllegalArgumentException("The dish does not exist");
                OrderDish orderDishCreated = new OrderDish();
                orderDishCreated.setIdDish(dish);
                orderDishCreated.setQuantity(orderDish.getQuantity());
                orderDishCreated.setIdOrder(order);
                orderDishes.add(orderDishCreated);
            }
            return orderDishes;
        }

}
