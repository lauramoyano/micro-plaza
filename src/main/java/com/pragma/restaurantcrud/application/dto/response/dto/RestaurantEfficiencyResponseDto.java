package com.pragma.restaurantcrud.application.dto.response.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantEfficiencyResponseDto {
    private Float efficiency;
    private List<OrderEfficiencyDto> orderEfficiencies;
}
