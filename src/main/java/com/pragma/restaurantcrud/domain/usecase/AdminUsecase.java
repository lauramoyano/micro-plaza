package com.pragma.restaurantcrud.domain.usecase;

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
            throw new IllegalArgumentException("Restaurant already exists");
        }
        validateRestaurant(restaurant);
        UserDto userDto = gateway.getUserById(restaurant.getIdOwner(), token);
        if (userDto == null) {
            throw new IllegalArgumentException("User not found");
        }
        if(!userDto.getRolName() .equals("OWNER")) {
            throw new IllegalArgumentException("User is not an owner");
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
            throw new IllegalArgumentException("Restaurant name cannot contain only numbers");
        }
    }

    private  void validateRestaurantFields(Restaurant restaurant) {
        System.out.println("Name: " + restaurant.getName());
        System.out.println("Address: " + restaurant.getAddress());
        System.out.println("IdOwner: " + restaurant.getIdOwner());
        System.out.println("Phone: " + restaurant.getPhone());
        System.out.println("Nit: " + restaurant.getNit());
        System.out.println("UrlLogo: " + restaurant.getUrlLogo());
        if (restaurant.getName() == null ||  restaurant.getName().replace(" ","").isEmpty() ||
                restaurant.getAddress() == null || restaurant.getAddress().replace(" ","").isEmpty() ||
                restaurant.getIdOwner() == null || restaurant.getPhone() == null || restaurant.getPhone().replace(" ","").isEmpty() ||
                restaurant.getNit() == null || restaurant.getUrlLogo() == null || restaurant.getUrlLogo().replace(" ","").isEmpty()
         ) {
            throw new IllegalArgumentException("All fields are required");
        }
    }

    //validate phone structure
    private void validatePhone(String phone) {
        if (!phone.matches("\\+?\\d{1,13}")) {
            throw new IllegalArgumentException("Phone must be a maximum of 13 characters and can contain the symbol +");
        }
        if (!phone.matches("\\+?\\d+")) {
            throw new IllegalArgumentException("Phone must be numeric and can contain the symbol +");
        }
    }

    // validate nit
    private void validateNit(Long nit) {
        if (nit == null) {
            throw new IllegalArgumentException("Nit is required");
        }
        if (String.valueOf(nit).matches("\\D")) {
            throw new IllegalArgumentException("Nit must be numeric");
        }
    }
    


}
