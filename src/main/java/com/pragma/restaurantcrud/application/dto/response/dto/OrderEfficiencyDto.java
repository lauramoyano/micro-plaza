package com.pragma.restaurantcrud.application.dto.response.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderEfficiencyDto {
    private Integer employeeId;
    private Integer orderId;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;

}
