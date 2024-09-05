package com.pragma.restaurantcrud.domain.spi.servicePortClient;

import com.pragma.restaurantcrud.application.dto.response.dto.OrderDto;
import com.pragma.restaurantcrud.application.dto.response.dto.RestaurantEfficiencyResponseDto;
import com.pragma.restaurantcrud.domain.models.TraceabilityModel;

import java.util.List;

public interface ITraceability {
    void saveTraceability(TraceabilityModel traceabilityModel);
    TraceabilityModel getTraceability(Integer idOrder, String currentStatus);
    List<TraceabilityModel> getAllTraceability(Integer idOrder);
    RestaurantEfficiencyResponseDto getRestaurantEfficiency(List<OrderDto> orderModelList, String token);
}
