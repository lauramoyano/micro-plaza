package com.pragma.restaurantcrud.infrastructure.output.client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderTraceabilityRequestDto {
    private String id;
    private Integer orderId;
    private Integer customerId;
    private String customerEmail;
    private String currentStatus;
    private Integer employeeId;
    private String employeeEmail;
    private LocalDateTime orderStartDate;
    private LocalDateTime orderEndDate;

}
