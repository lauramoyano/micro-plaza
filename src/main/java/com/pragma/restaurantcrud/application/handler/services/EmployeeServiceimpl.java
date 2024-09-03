package com.pragma.restaurantcrud.application.handler.services;

import com.pragma.restaurantcrud.application.dto.response.CreateOrderResponse;
import com.pragma.restaurantcrud.application.dto.response.OrderNotifyResponse;
import com.pragma.restaurantcrud.application.dto.response.OrdersPageResponse;
import com.pragma.restaurantcrud.application.handler.IEmployeeServiceHandler;
import com.pragma.restaurantcrud.application.mapper.response.ICustomerResponseMapper;
import com.pragma.restaurantcrud.application.mapper.response.IEmployeeResponseMapper;
import com.pragma.restaurantcrud.domain.api.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Transactional
@Service
public class EmployeeServiceimpl  implements IEmployeeServiceHandler {

    private final IEmployeeService employeeService;
    private final IEmployeeResponseMapper employeeResponseMapper;
    private  final ICustomerResponseMapper customerResponseMapper;

    @Override
    public Page<OrdersPageResponse> getAllOrdersFilterByStatus(Integer size, Integer page, String status, String token) {
        return employeeService.getAllOrdersFilterByStatusAndSizeItemsByPage(size, page, status, token)
                .map(employeeResponseMapper::toOrder);

    }

    @Override
    public List<CreateOrderResponse> changeStatusPreparation(List<Long> idOrders, String token) {
        return this.employeeService.assignEmployeeToOrderAndChangeStatusToInPreparation(idOrders, token).stream()
                .map(customerResponseMapper::toOrderCreatedResponse).collect(Collectors.toList());
    }

    @Override
    public OrderNotifyResponse changeOrderStatusToReadyAndNotifyCustomer(Long idOrder, String token) {
        return this.employeeResponseMapper.orderToOrderNotifyResponse(this.employeeService.orderReadyAndNotifyCustomer(idOrder, token));
    }

    @Override
    public OrderNotifyResponse changeOrderStatusToDeliveredAndNotifyCustomer(Long idPin, String token) {
        return this.employeeResponseMapper.orderToOrderNotifyResponse(this.employeeService.orderDelivered(idPin, token));
    }

}
