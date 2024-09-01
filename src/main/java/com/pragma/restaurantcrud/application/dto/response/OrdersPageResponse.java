package com.pragma.restaurantcrud.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersPageResponse {

    private Long idOrder;
    private Long idRestaurant;
    private Date date;
    private String status;
    private List<DishInOrderResponse> dishes;
}
