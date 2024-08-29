package com.pragma.restaurantcrud.application.handler.services;


import com.pragma.restaurantcrud.application.dto.request.CreateDishRequest;
import com.pragma.restaurantcrud.application.dto.request.UpdateDishRequest;
import com.pragma.restaurantcrud.application.dto.request.VisibilityDishRequest;
import com.pragma.restaurantcrud.application.dto.response.CreateDishResponse;
import com.pragma.restaurantcrud.application.dto.response.UpdateDishResponse;
import com.pragma.restaurantcrud.application.handler.IOwnerServiceHandler;
import com.pragma.restaurantcrud.application.mapper.request.IOwnerRequestMapper;
import com.pragma.restaurantcrud.application.mapper.response.IOwnerResponseMapper;
import com.pragma.restaurantcrud.domain.api.IOwnerService;
import com.pragma.restaurantcrud.domain.models.Dish;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Transactional
@Service
public class OwnerServiceimpl implements IOwnerServiceHandler {
    private final IOwnerResponseMapper ownerResponseMapper;
    private final IOwnerRequestMapper ownerRequestMapper;
    private final IOwnerService ownerService;

    @Override
    public CreateDishResponse createDish(CreateDishRequest createDishRequest) {
        Dish dish = ownerRequestMapper.createDishRequestDtoToDishModel(createDishRequest);
        Dish dishCreated = ownerService.createDish(dish);
        return ownerResponseMapper.dishToCreateDishResponse(dishCreated);
    }

    @Override
    public UpdateDishResponse updateDish(UpdateDishRequest updateDishRequest) {
        Dish dish = ownerRequestMapper.updateDishRequestDtoToDishModel(updateDishRequest);
        Dish dishUpdated = ownerService.updateDish(dish);
        return ownerResponseMapper.dishToUpdateDishResponse(dishUpdated);
    }

    @Override
    public UpdateDishResponse setDishVisibility(Long idDish, Long idRestaurant, boolean visibility, String token) {
        Dish dish = ownerService.setDishVisibility(idDish, idRestaurant, visibility, token);
        return ownerResponseMapper.dishToUpdateDishResponse(dish);
    }

}
