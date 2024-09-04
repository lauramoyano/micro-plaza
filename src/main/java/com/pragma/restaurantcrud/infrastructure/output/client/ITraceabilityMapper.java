package com.pragma.restaurantcrud.infrastructure.output.client;

import com.pragma.restaurantcrud.domain.models.PinMessage;
import com.pragma.restaurantcrud.domain.models.TraceabilityModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITraceabilityMapper {
    OrderTraceabilityRequestDto mapToOrderTraceabilityRequestDto(TraceabilityModel traceabilityModel);
    List<OrderTraceabilityRequestDto> mapToOrderTraceabilityRequestDtoList(List<TraceabilityModel> traceabilityModelList);
    TraceabilityModel mapToTraceabilityModel(OrderTraceabilityRequestDto orderTraceabilityRequestDto);
}
