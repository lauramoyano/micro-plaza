package com.pragma.restaurantcrud.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishesPageResponse {

    private Long idCategory;
    private String name;
    private List<DishResponse> dishes;
}
