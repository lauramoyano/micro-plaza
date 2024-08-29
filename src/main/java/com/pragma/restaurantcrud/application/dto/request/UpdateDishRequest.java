package com.pragma.restaurantcrud.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDishRequest {

    private Long idDish;
    private String description;
    private Double price;
    private Long idRestaurant;
}
