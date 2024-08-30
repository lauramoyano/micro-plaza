package com.pragma.restaurantcrud.infrastructure.restControllerRestaurant;

import com.pragma.restaurantcrud.application.dto.request.CreateOrderRequest;
import com.pragma.restaurantcrud.application.dto.response.CreateDishResponse;
import com.pragma.restaurantcrud.application.dto.response.CreateOrderResponse;
import com.pragma.restaurantcrud.application.dto.response.DishesPageResponse;
import com.pragma.restaurantcrud.application.dto.response.RestaurantPageResponse;
import com.pragma.restaurantcrud.application.handler.ICustomerServiceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/plaza/customer")
public class CustomerController {
    private final ICustomerServiceHandler customerServiceHandler;

    @GetMapping("/restaurants")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Page<RestaurantPageResponse>> getRestaurants(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size) {
       Page<RestaurantPageResponse> restaurants = customerServiceHandler.getAllRestaurants(page, size);
       return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/dishesFromRestaurant/{idRestaurant}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Page<DishesPageResponse>> getDishesFromRestaurant(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                            @PathVariable Long idRestaurant) {
        Page<DishesPageResponse> dishes = customerServiceHandler.getAllDishes(page, size, idRestaurant);
        return ResponseEntity.ok(dishes);
    }

    @PostMapping("/createOrder")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        final CreateOrderResponse createOrderResponse = customerServiceHandler.createOrder(createOrderRequest, token);
        return new ResponseEntity<>(createOrderResponse, HttpStatus.CREATED);
    }
}
