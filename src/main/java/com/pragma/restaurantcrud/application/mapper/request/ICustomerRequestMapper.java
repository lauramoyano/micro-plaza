package com.pragma.restaurantcrud.application.mapper.request;

import com.pragma.restaurantcrud.application.dto.request.CreateOrderRequest;
import com.pragma.restaurantcrud.application.dto.request.DishInOrderRequest;
import com.pragma.restaurantcrud.domain.models.Dish;
import com.pragma.restaurantcrud.domain.models.Order;
import com.pragma.restaurantcrud.domain.models.OrderDish;
import com.pragma.restaurantcrud.domain.models.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ICustomerRequestMapper {

    // Mapeo del CreateOrderRequest a Order, incluyendo la conversión de List<DishInOrderRequest> a List<OrderDish>
    @Mapping(target = "restaurant", source = "idRestaurant", qualifiedByName = "mapRestaurant")
    @Mapping(target = "orderDishes", source = "orderRequest.dishes")
    @Mapping(target="idOrder", ignore = true)
    Order orderRequestToOrder(CreateOrderRequest orderRequest);

    // Mapeo de DishInOrderRequest a OrderDish
    @Mapping(target="idOrderDish", ignore = true)

    @Mapping(target = "dish", source = "idDish", qualifiedByName = "mapDish")
    @Mapping(target = "order", ignore = true)  // Ignora el mapeo del objeto Order aquí
    OrderDish dishInOrderRequestToOrderDish(DishInOrderRequest dishInOrderRequest);

    // Métodos auxiliares para mapear objetos personalizados
    @Named("mapDish")
    default Dish mapDish(Long idDish) {
        if (idDish == null) {
            return null;
        }
        Dish dish = new Dish();
        dish.setIdDish(idDish);
        return dish;
    }

    @Named("mapRestaurant")
    default Restaurant mapRestaurant(Long idRestaurant) {
        if (idRestaurant == null) {
            return null;
        }
        Restaurant restaurant = new Restaurant();
        restaurant.setIdRestaurant(idRestaurant);
        return restaurant;
    }
}
