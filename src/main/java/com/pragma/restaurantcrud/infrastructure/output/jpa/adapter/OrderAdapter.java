package com.pragma.restaurantcrud.infrastructure.output.jpa.adapter;

import com.pragma.restaurantcrud.domain.models.Order;
import com.pragma.restaurantcrud.domain.spi.persistence.IOrderPersistencePort;
import com.pragma.restaurantcrud.infrastructure.output.jpa.entity.OrderEntity;
import com.pragma.restaurantcrud.infrastructure.output.jpa.mapper.IOrderEntityMapper;
import com.pragma.restaurantcrud.infrastructure.output.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OrderAdapter implements IOrderPersistencePort {

    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderMapper;

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = orderMapper.mapOrderToOrderEntity(order);
        OrderEntity orderEntitySaved = orderRepository.save(orderEntity);
        return orderMapper.mapOrderEntityToOrder(orderEntitySaved);
    }

    public List<Order> findByRestaurantIdAndCustomerId(Long idRestaurant, Long customerId) {
        return orderRepository.findAllByIdCustomerAndRestaurantIdRestaurant(customerId, idRestaurant)
                .stream().map(orderMapper::mapOrderEntityToOrder).collect(Collectors.toList());

    }

    @Override
    public Page<Order> findAllOrdersByStatusAndSizeItemsByPage(Pageable pageable, Long idRestaurant, String status) {
        return this.orderRepository.findAllByRestaurantIdRestaurantAndStatus( idRestaurant, status, pageable)
                .map(orderMapper::mapOrderEntityToOrder);
    }


    @Override
    public Order findById(Long idOrder) {
        return orderMapper.mapOrderEntityToOrder(orderRepository.findById(idOrder).orElse(null));
    }


    @Override
    public boolean existsById(Long idOrder) {
        return orderRepository.existsById(idOrder);
    }


}
