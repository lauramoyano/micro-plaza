package com.pragma.restaurantcrud.domain.spi.servicePortClient;

import com.pragma.restaurantcrud.domain.models.PinMessage;
import com.pragma.restaurantcrud.domain.models.TraceabilityModel;

import java.util.List;

public interface ITraceability {
    void saveTraceability(TraceabilityModel traceabilityModel);
    TraceabilityModel getTraceability(Long idOrder);
    List<TraceabilityModel> getAllTraceability(Integer idOrder);
}
