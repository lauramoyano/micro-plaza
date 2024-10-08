package com.pragma.restaurantcrud.application.mapper.response;

import com.pragma.restaurantcrud.application.dto.response.*;
import com.pragma.restaurantcrud.domain.models.Dish;
import com.pragma.restaurantcrud.domain.models.Order;
import com.pragma.restaurantcrud.domain.models.Restaurant;
import com.pragma.restaurantcrud.domain.models.TraceabilityModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface ICustomerResponseMapper {

    RestaurantPageResponse toRestaurantPageResponse(Restaurant restaurant);

    @Mapping(source = "key", target = "idCategory")
    @Mapping(target = "name", expression = "java(entry.getValue().isEmpty() ? null : entry.getValue().get(0).getCategory().getName())")
    @Mapping(source = "value", target = "dishes")
    DishesPageResponse toDishesPageResponse(Map.Entry<Long, List<Dish>> entry);

    DishResponse toDishResponse(Dish dish);

    List<DishResponse> toDishResponseList(List<Dish> dishes);

    @Mapping(target ="idOrder", source = "idOrder")
    CreateOrderResponse toOrderCreatedResponse(Order order);

    @Mapping(target = "idOrder", source = "idOrder")
    OrderNotifyResponse toOrderNotifyResponse(Order order);

    OrderTraceabilityResponseDto toOrderTraceabilityResponseDto(TraceabilityModel traceabilityModel);


}
