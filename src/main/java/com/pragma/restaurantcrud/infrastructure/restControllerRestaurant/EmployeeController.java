package com.pragma.restaurantcrud.infrastructure.restControllerRestaurant;

import com.pragma.restaurantcrud.application.dto.response.CreateOrderResponse;
import com.pragma.restaurantcrud.application.dto.response.OrdersPageResponse;
import com.pragma.restaurantcrud.application.dto.response.RestaurantPageResponse;
import com.pragma.restaurantcrud.application.handler.IEmployeeServiceHandler;
import com.pragma.restaurantcrud.domain.models.EmployeeRestaurant;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/plaza/employee")
public class EmployeeController {
    private final IEmployeeServiceHandler employeeServiceHandler;


    @GetMapping("/GetAllOrders")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<Page<OrdersPageResponse>> getAllOrders(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                   @RequestParam(value = "size", defaultValue = "1") Integer size, @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestParam(value = "status", defaultValue = "PENDIENTE") String status) {
        Page<OrdersPageResponse> orders =   employeeServiceHandler.getAllOrdersFilterByStatus(size, page,  status, token);
        return ResponseEntity.ok(orders);
    }

    @PatchMapping(value = "/orderAssignation")
    @PreAuthorize(value = "hasRole('EMPLOYEE')")
    public ResponseEntity<List<CreateOrderResponse>> assignEmployeeToOrderAndChangeStatus(
            @RequestParam(name = "idOrder") List<Long> idOrders,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
       final List<CreateOrderResponse> orders = employeeServiceHandler.changeStatusPreparation(idOrders, token);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
