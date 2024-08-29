package com.pragma.restaurantcrud.application.dto.request;

import com.pragma.restaurantcrud.domain.models.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDishRequest {
    private String name;
    private String description;
    private Double price;
    private Long idCategory;
    private String urlImage;
    private Long idRestaurant;


}
