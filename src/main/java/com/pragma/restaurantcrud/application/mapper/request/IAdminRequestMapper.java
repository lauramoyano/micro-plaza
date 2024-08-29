package com.pragma.restaurantcrud.application.mapper.request;
import com.pragma.restaurantcrud.application.dto.request.CreateRestaurantDto;
import com.pragma.restaurantcrud.domain.models.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IAdminRequestMapper {

    @Mapping(target = "idRestaurant", ignore = true)
    Restaurant createRestaurantDtoToRestaurant(CreateRestaurantDto createRestaurantDto);
}
