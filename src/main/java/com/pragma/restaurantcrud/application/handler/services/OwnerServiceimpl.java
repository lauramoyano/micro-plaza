package com.pragma.restaurantcrud.application.handler.services;


import com.pragma.restaurantcrud.application.dto.request.CreateDishRequest;
import com.pragma.restaurantcrud.application.dto.request.EmployeeRestaurantRequest;
import com.pragma.restaurantcrud.application.dto.request.UpdateDishRequest;
import com.pragma.restaurantcrud.application.dto.response.*;
import com.pragma.restaurantcrud.application.dto.response.dto.OrderDto;
import com.pragma.restaurantcrud.application.dto.response.dto.RestaurantEfficiencyResponseDto;
import com.pragma.restaurantcrud.application.handler.IOwnerServiceHandler;
import com.pragma.restaurantcrud.application.mapper.request.IEmployeeRequestMapper;
import com.pragma.restaurantcrud.application.mapper.request.IOwnerRequestMapper;
import com.pragma.restaurantcrud.application.mapper.response.IEmployeeResponseMapper;
import com.pragma.restaurantcrud.application.mapper.response.IOwnerResponseMapper;
import com.pragma.restaurantcrud.domain.api.IEmployeeService;
import com.pragma.restaurantcrud.domain.api.IOwnerService;
import com.pragma.restaurantcrud.domain.models.Dish;
import com.pragma.restaurantcrud.domain.models.EmployeeRestaurant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Transactional
@Service
public class OwnerServiceimpl implements IOwnerServiceHandler {
    private final IOwnerResponseMapper ownerResponseMapper;
    private final IOwnerRequestMapper ownerRequestMapper;
    private final IOwnerService ownerService;
    private final IEmployeeRequestMapper employeeRequestMapper;
    private final IEmployeeResponseMapper   employeeResponseMapper;
    private final IEmployeeService employeeService;

    @Override
    public CreateDishResponse createDish(CreateDishRequest createDishRequest, String token) {
        Dish dish = ownerRequestMapper.createDishRequestDtoToDishModel(createDishRequest);
        Dish dishCreated = ownerService.createDish(dish, token);
        return ownerResponseMapper.dishToCreateDishResponse(dishCreated);
    }

    @Override
    public UpdateDishResponse updateDish(UpdateDishRequest updateDishRequest, String token) {
        Dish dish = ownerRequestMapper.updateDishRequestDtoToDishModel(updateDishRequest);
        Dish dishUpdated = ownerService.updateDish(dish, token);
        return ownerResponseMapper.dishToUpdateDishResponse(dishUpdated);
    }

    @Override
    public UpdateDishResponse setDishVisibility(Long idDish, Long idRestaurant, boolean visibility, String token) {
        Dish dish = ownerService.setDishVisibility(idDish, idRestaurant, visibility, token);
        return ownerResponseMapper.dishToUpdateDishResponse(dish);
    }

    @Override
    public EmployeeRestaurantResponse saveUserEmployeeInTheRestaurant(EmployeeRestaurantRequest employeeRestaurantRequest, String token) {
        EmployeeRestaurant employeeRestaurant = employeeRequestMapper.employeeRestaurantRequestDtoToEmployeeRestaurantModel(employeeRestaurantRequest);
        EmployeeRestaurant employeeRestaurantSaved = employeeService.createEmployeeRestaurant(employeeRestaurant, token);
        return employeeResponseMapper.employeeRestaurantToEmployeeRestaurantResponse(employeeRestaurantSaved);
    }

    @Override
    public RestaurantEfficiencyResponseDto getRestaurantEfficiency(List<OrderDto> orderModelList, String token) {
        return null;
    }


}
