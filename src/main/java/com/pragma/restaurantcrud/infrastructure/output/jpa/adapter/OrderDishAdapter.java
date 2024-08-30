package com.pragma.restaurantcrud.infrastructure.output.jpa.adapter;

import com.pragma.restaurantcrud.domain.models.OrderDish;
import com.pragma.restaurantcrud.domain.spi.persistence.IOrderDishPersistencePort;
import com.pragma.restaurantcrud.infrastructure.output.jpa.entity.OrderDishEntity;
import com.pragma.restaurantcrud.infrastructure.output.jpa.mapper.IOrderEntityMapper;
import com.pragma.restaurantcrud.infrastructure.output.jpa.repository.IOrderDishRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OrderDishAdapter implements IOrderDishPersistencePort {

    private final IOrderDishRepository orderDishRepository;
    private final IOrderEntityMapper orderMapper;

    @Override
    public OrderDish save(OrderDish orderDish) {
        return orderMapper.mapOrderDishEntityToOrderDish(orderDishRepository.save(orderMapper.mapOrderDishToOrderDishEntity(orderDish)));
    }

    @Override
    public List<OrderDish> saveAll(List<OrderDish> orderDishes) {
       List<OrderDishEntity> orderDishList = orderDishes.stream().map(orderMapper::mapOrderDishToOrderDishEntity).collect(Collectors.toList());
       List<OrderDishEntity> orderDishListMapped = orderDishRepository.saveAll(orderDishList);
         return orderDishListMapped.stream().map(orderMapper::mapOrderDishEntityToOrderDish).collect(Collectors.toList());
    }
}
