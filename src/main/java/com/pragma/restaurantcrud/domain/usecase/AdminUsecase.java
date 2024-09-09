package com.pragma.restaurantcrud.domain.usecase;

import com.pragma.restaurantcrud.domain.exeptions.*;
import com.pragma.restaurantcrud.infrastructure.output.client.UserDto;
import com.pragma.restaurantcrud.domain.api.IAdminService;
import com.pragma.restaurantcrud.domain.models.Restaurant;
import com.pragma.restaurantcrud.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.restaurantcrud.domain.spi.servicePortClient.IGateway;

public class AdminUsecase implements IAdminService {

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IGateway gateway;

    public AdminUsecase(IRestaurantPersistencePort restaurantPersistencePort, IGateway gateway) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.gateway = gateway;
    }

    @Override
    public boolean existsRestaurantByName(String name) {
        return restaurantPersistencePort.existsRestaurantByName(name);
    }
    @Override
    public Restaurant createRestaurant(Restaurant restaurant, String token) {
        if(existsRestaurantByName(restaurant.getName())) {
            throw new AlreadyExist("Restaurant already exists");

        }
        validateRestaurant(restaurant);
        UserDto userDto = gateway.getUserById(restaurant.getIdOwner(), token);
        if (userDto == null) {
            throw new NotFound("User not found");
        }
        if(!userDto.getRolName() .equals("OWNER")) {
            throw new NotBelong( "User is not an owner");
        }
        return restaurantPersistencePort.saveRestaurant(restaurant);

    }

    private void validateRestaurant(Restaurant restaurant) {
        validateRestaurantFields(restaurant);
        validatePhone(restaurant.getPhone());
        validateNit(restaurant.getNit());
        validateRestaurantName(restaurant.getName());

    }

    private void validateRestaurantName(String name) {
        if (name.matches("\\d+")) {
            throw new InvalidData("Restaurant name cannot contain only numbers");
        }
    }

    private void validateRestaurantFields(Restaurant restaurant) {
        if (restaurant.getName() == null || restaurant.getName().replace(" ", "").isEmpty()) {
            throw new EmptyFields("Name is required");
        }
        if (restaurant.getAddress() == null || restaurant.getAddress().replace(" ", "").isEmpty()) {
            throw new EmptyFields("Address is required");
        }
        if (restaurant.getIdOwner() == null) {
            throw new EmptyFields("IdOwner is required");
        }
        if (restaurant.getPhone() == null || restaurant.getPhone().replace(" ", "").isEmpty()) {
            throw new EmptyFields("Phone is required");
        }
        if (restaurant.getNit() == null) {
            throw new EmptyFields("Nit is required");
        }
        if (restaurant.getUrlLogo() == null || restaurant.getUrlLogo().replace(" ", "").isEmpty()) {
            throw new EmptyFields("UrlLogo is required");
        }
    }


    //validate phone structure
    private void validatePhone(String phone) {
        if (!phone.matches("(\\+\\d{12})|(\\d{10})")) {
            throw new InvalidData("Phone must be a maximum of 13 characters and can contain the symbol +");
        }

    }

    // validate nit
    private void validateNit(Long nit) {
        if (nit == null) {
            throw new EmptyFields("Nit is required");
        }
        if (!String.valueOf(nit).matches("\\d+")) {
            throw new InvalidData("Nit must be numeric");
        }
    }
    


}
