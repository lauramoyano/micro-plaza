package com.pragma.restaurantcrud.infrastructure.restControllerRestaurant;

import com.pragma.restaurantcrud.application.dto.request.CreateDishRequest;
import com.pragma.restaurantcrud.application.dto.request.EmployeeRestaurantRequest;
import com.pragma.restaurantcrud.application.dto.request.UpdateDishRequest;
import com.pragma.restaurantcrud.application.dto.response.CreateDishResponse;
import com.pragma.restaurantcrud.application.dto.response.EmployeeRestaurantResponse;
import com.pragma.restaurantcrud.application.dto.response.UpdateDishResponse;
import com.pragma.restaurantcrud.application.handler.IOwnerServiceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/plaza/owner")
public class OwnerController {
    private final IOwnerServiceHandler ownerServiceHandler;

    @PostMapping("/createDish")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<CreateDishResponse> createDish(@RequestBody CreateDishRequest createDishRequest) {
        CreateDishResponse createDishResponse = ownerServiceHandler.createDish(createDishRequest);
        return new ResponseEntity<>(createDishResponse, HttpStatus.CREATED);
    }

    @PatchMapping("/updateDish")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<UpdateDishResponse> updateDish(@RequestBody UpdateDishRequest updateDishRequest) {
        UpdateDishResponse updateDishResponse = ownerServiceHandler.updateDish(updateDishRequest);
        return new ResponseEntity<>(updateDishResponse, HttpStatus.OK);
    }


    @PutMapping("/restaurant/{idRestaurant}/dish/{idDish}/visibility/{visibility}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<UpdateDishResponse> setDishVisibility(@PathVariable("idRestaurant") Long idRestaurant,
                                                               @PathVariable("idDish") Long idDish,
                                                               @PathVariable("visibility") Boolean visibility, @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        UpdateDishResponse updateDishResponse = ownerServiceHandler.setDishVisibility(idDish, idRestaurant, visibility, token);
        return new ResponseEntity<>(updateDishResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<EmployeeRestaurantResponse> saveEmployeeUserRestaurant(@RequestBody EmployeeRestaurantRequest employeeRestaurant, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return new ResponseEntity<>(this.ownerServiceHandler.saveUserEmployeeInTheRestaurant(employeeRestaurant, token), HttpStatus.CREATED);

    }


}
