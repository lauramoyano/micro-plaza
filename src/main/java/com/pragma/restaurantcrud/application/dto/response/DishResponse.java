package com.pragma.restaurantcrud.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishResponse {

        private Long idDish;
        private String name;
        private String description;
        private Double price;
        private String urlImage;

}
