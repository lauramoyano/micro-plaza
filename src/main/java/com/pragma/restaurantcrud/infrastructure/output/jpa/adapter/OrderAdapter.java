package com.pragma.restaurantcrud.infrastructure.output.jpa.adapter;

import com.pragma.restaurantcrud.domain.models.Order;
import com.pragma.restaurantcrud.domain.spi.persistence.IOrderPersistencePort;
import com.pragma.restaurantcrud.infrastructure.output.jpa.entity.OrderEntity;
import com.pragma.restaurantcrud.infrastructure.output.jpa.mapper.IOrderEntityMapper;
import com.pragma.restaurantcrud.infrastructure.output.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OrderAdapter implements IOrderPersistencePort {

    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderMapper;

    @Override
    public Order save(Order order) {
        final OrderEntity orderEntity = orderMapper.mapOrderToOrderEntity(order);
        final OrderEntity orderEntitySaved = orderRepository.save(orderEntity);
        return orderMapper.mapOrderEntityToOrder(orderEntitySaved);
    }

    public List<Order> findByRestaurantIdAndCustomerId(Long idRestaurant, Long customerId) {
        return orderRepository.findAllByIdCustomerAndRestaurantIdRestaurant(customerId, idRestaurant)
                .stream().map(orderMapper::mapOrderEntityToOrder).collect(Collectors.toList());

    }



    @Override
    public boolean existsById(Long idOrder) {
        return orderRepository.existsById(idOrder);
    }


}
