package com.pragma.restaurantcrud.domain.api;

import com.pragma.restaurantcrud.domain.models.EmployeeRestaurant;
import com.pragma.restaurantcrud.domain.models.Order;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IEmployeeService {

    EmployeeRestaurant createEmployeeRestaurant(EmployeeRestaurant employeeRestaurant, String token);
    Page<Order> getAllOrdersFilterByStatusAndSizeItemsByPage(Integer size, Integer page, String status, String token);
    List<Order>  assignEmployeeToOrderAndChangeStatusToInPreparation(List<Long> idOrders, String token);
    Order orderReadyAndNotifyCustomer(Long idOrder, String token);
    Order orderDelivered(Long idOrder, String token);



}
