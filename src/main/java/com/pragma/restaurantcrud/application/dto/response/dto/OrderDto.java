package com.pragma.restaurantcrud.application.dto.response.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
    private Integer id;
    private Integer restaurant;
    private Integer userId;
    private String status;
    private Integer employee;
}
