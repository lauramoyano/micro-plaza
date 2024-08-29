package com.pragma.restaurantcrud.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisibilityDishRequest {
    private Long idDish;
    private Boolean enabled;
}
