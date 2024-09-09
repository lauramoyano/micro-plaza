package com.pragma.restaurantcrud.application.handler.services;

import com.pragma.restaurantcrud.application.dto.request.CreateOrderRequest;
import com.pragma.restaurantcrud.application.dto.response.*;
import com.pragma.restaurantcrud.application.handler.ICustomerServiceHandler;
import com.pragma.restaurantcrud.application.mapper.request.ICustomerRequestMapper;
import com.pragma.restaurantcrud.application.mapper.response.ICustomerResponseMapper;
import com.pragma.restaurantcrud.domain.api.ICustomerService;
import com.pragma.restaurantcrud.domain.models.Order;
import com.pragma.restaurantcrud.domain.models.OrderDish;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Transactional
public class CustomerServiceimpl implements ICustomerServiceHandler {
    private final ICustomerService customerService;
    private final ICustomerResponseMapper customerResponseMapper;
    private final ICustomerRequestMapper customerRequestMapper;
    @Override
    public Page<RestaurantPageResponse>  getAllRestaurants(Integer page, Integer size) {
        return customerService.findAllByOrderByNameAsc(page, size).map(customerResponseMapper::toRestaurantPageResponse);

    }
    @Override
    public Page<DishesPageResponse> getAllDishes(Integer page, Integer size, Long idRestaurant) {
        return customerService.findAllDishesByRestaurantId(idRestaurant, page, size)
                .stream()
                .collect(Collectors.groupingBy(dish -> dish.getCategory().getIdCategory()))
                .entrySet()
                .stream()
                .map(customerResponseMapper::toDishesPageResponse)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        content -> new PageImpl<>(content, PageRequest.of(page, size), content.size())
                ));
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest, String token) {
        Order order = customerRequestMapper.orderRequestToOrder(createOrderRequest);
        List<OrderDish> orderDishes = createOrderRequest.getDishes().stream()
                .map(customerRequestMapper::dishInOrderRequestToOrderDish)
                        .toList();

        orderDishes.forEach(orderDish -> orderDish.setOrder(order));

        order.setOrderDishes(orderDishes);
        return customerResponseMapper.toOrderCreatedResponse(customerService.createOrder(order, token));
    }

    @Override
    public OrderNotifyResponse cancelOrder(Long idOrder, String token) {
        return customerResponseMapper.toOrderNotifyResponse(customerService.cancelOrder(idOrder, token));
    }

    @Override
    public List<OrderTraceabilityResponseDto> orderTraceability(Long idOrder, String token) {
        return customerService.findTraceabilityByOrderId(idOrder, token)
                .stream()
                .map(customerResponseMapper::toOrderTraceabilityResponseDto)
                .collect(Collectors.toList());
    }
}
