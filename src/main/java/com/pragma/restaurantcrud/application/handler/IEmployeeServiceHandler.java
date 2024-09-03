package com.pragma.restaurantcrud.application.handler;

import com.pragma.restaurantcrud.application.dto.response.CreateOrderResponse;
import com.pragma.restaurantcrud.application.dto.response.OrderNotifyResponse;
import com.pragma.restaurantcrud.application.dto.response.OrdersPageResponse;
import org.springframework.data.domain.Page;

import java.util.List;


public interface IEmployeeServiceHandler {

    Page<OrdersPageResponse> getAllOrdersFilterByStatus(Integer size, Integer page, String status, String token);

    List<CreateOrderResponse> changeStatusPreparation(List<Long> idOrders, String token);

    OrderNotifyResponse changeOrderStatusToReadyAndNotifyCustomer(Long idOrder, String token);

    OrderNotifyResponse changeOrderStatusToDeliveredAndNotifyCustomer(Long idPin, String token);
}
