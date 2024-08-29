package com.pragma.restaurantcrud.infrastructure.restControllerRestaurant;

import com.pragma.restaurantcrud.application.dto.request.CreateRestaurantDto;
import com.pragma.restaurantcrud.application.dto.response.CreateRestaurantResponse;
import com.pragma.restaurantcrud.application.handler.IAdminServiceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/plaza/admin")
public class AdminController {

    private final IAdminServiceHandler adminService;


    @PostMapping("/createRestaurant")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateRestaurantResponse> createRestaurant(@RequestBody CreateRestaurantDto createRestaurantDto,@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        CreateRestaurantResponse createRestaurantResponse = adminService.createRestaurant(createRestaurantDto, token);
        return new ResponseEntity<>(createRestaurantResponse, HttpStatus.CREATED);

    }
}
