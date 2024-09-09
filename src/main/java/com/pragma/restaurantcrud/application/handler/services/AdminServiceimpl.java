package com.pragma.restaurantcrud.application.handler.services;

import com.pragma.restaurantcrud.application.dto.request.CreateRestaurantDto;
import com.pragma.restaurantcrud.application.dto.response.CreateRestaurantResponse;
import com.pragma.restaurantcrud.application.handler.IAdminServiceHandler;
import com.pragma.restaurantcrud.application.mapper.request.IAdminRequestMapper;
import com.pragma.restaurantcrud.application.mapper.response.IAdminResponseMapper;
import com.pragma.restaurantcrud.domain.api.IAdminService;
import com.pragma.restaurantcrud.domain.models.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Transactional
@Service
public class AdminServiceimpl  implements IAdminServiceHandler {
    private  final IAdminService adminPort;
    private  final IAdminResponseMapper iAdminResponseMapper;
    private  final IAdminRequestMapper iAdminRequestMapper;

    @Override
    public CreateRestaurantResponse  createRestaurant(CreateRestaurantDto createRestaurantDto, String token)  {
        Restaurant restaurant = iAdminRequestMapper.createRestaurantDtoToRestaurant(createRestaurantDto);
        Restaurant restaurantCreated =adminPort.createRestaurant(restaurant, token);
        return iAdminResponseMapper.restaurantToCreateRestaurantResponse(restaurantCreated);

    }

}
