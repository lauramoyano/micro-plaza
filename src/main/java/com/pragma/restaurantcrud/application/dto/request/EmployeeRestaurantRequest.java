package com.pragma.restaurantcrud.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRestaurantRequest {
    private Long idEmployee;
    private Long idRestaurant;
}
