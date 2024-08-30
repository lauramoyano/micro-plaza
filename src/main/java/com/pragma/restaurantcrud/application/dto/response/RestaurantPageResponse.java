package com.pragma.restaurantcrud.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantPageResponse {

    private Long idRestaurant;
    private String name;
    private String urlLogo;
}
