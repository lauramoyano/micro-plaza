package com.pragma.restaurantcrud.domain.usecase;

import com.pragma.restaurantcrud.application.dto.response.dto.OrderDto;
import com.pragma.restaurantcrud.application.dto.response.dto.RestaurantEfficiencyResponseDto;
import com.pragma.restaurantcrud.domain.api.IOwnerService;
import com.pragma.restaurantcrud.domain.exeptions.EmptyFields;
import com.pragma.restaurantcrud.domain.exeptions.InvalidData;
import com.pragma.restaurantcrud.domain.exeptions.NotBelong;
import com.pragma.restaurantcrud.domain.exeptions.NotFound;
import com.pragma.restaurantcrud.domain.models.*;
import com.pragma.restaurantcrud.domain.spi.persistence.ICategoryPersistencePort;
import com.pragma.restaurantcrud.domain.spi.persistence.IDishPersistencePort;
import com.pragma.restaurantcrud.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.restaurantcrud.domain.spi.servicePortClient.IGateway;
import com.pragma.restaurantcrud.infrastructure.config.securityClient.JwtProvider;

import java.util.List;

public class OwnerUsecase implements IOwnerService {
    private final IDishPersistencePort dishPersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IGateway gateway;
    private final JwtProvider jwtProvider;


    public OwnerUsecase(IDishPersistencePort dishPersistencePort, ICategoryPersistencePort categoryPersistencePort
            , IRestaurantPersistencePort restaurantPersistencePort, IGateway gateway, JwtProvider jwtProvider) {
        this.dishPersistencePort = dishPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.gateway = gateway;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public Dish createDish(Dish dish, String token) {
        User userOwnerAuthenticated = getUserOwnerAuthenticated(token);
        Long idRestaurant = dish.getRestaurant().getIdRestaurant();
        restaurantBelongsToUser(idRestaurant, userOwnerAuthenticated.getId());
        if(dish.getPrice() <= 0)
            throw new InvalidData("Price must be greater than zero");
        Restaurant restaurantModel = this.restaurantPersistencePort.findRestaurantById(dish.getRestaurant().getIdRestaurant());
        if(restaurantModel == null)
            throw new NotFound("The restaurant does not exist");
        Category categoryModel = this.categoryPersistencePort.findByIdCategory(dish.getCategory().getIdCategory());
        if(categoryModel == null)
            throw new NotFound("The category does not exist");

        dish.setRestaurant(restaurantModel);
        dish.setCategory(categoryModel);
        dish.setEnabled(true);
        return this.dishPersistencePort.save(dish);
    }


    private void validateDish(Dish dish) {
        validateDishFields(dish);
        validateDishAssociation(dish);
    }

    private void validateDishFields(Dish dish) {
        if ( dish.getName().replace(" ", "").isEmpty() ||
                dish.getPrice() == null || dish.getPrice() <= 0 ||
                dish.getDescription().replace(" ", "").isEmpty() ||
                dish.getUrlImage().replace(" ", "").isEmpty() ||
                dish.getCategory() == null) {
            throw new EmptyFields("All fields are required and price must be a positive integer greater than 0");
        }
    }

    private void validateDishAssociation(Dish dish) {
        if (dish.getRestaurant() == null) {
            throw new NotFound("Dish must be associated with a restaurant");
        }
    }

    @Override
    public Dish updateDish(Dish dish, String token) {
        Dish existingDish = dishPersistencePort.findByIdDish(dish.getIdDish());
        if (existingDish == null) {
            throw new NotFound("Dish not found");
        }
        String email = jwtProvider.getAuthentication(token.replace("Bearer ", "").trim()).getName();
        User user = this.gateway.getUserByEmail(email, token);

        if(!user.getId().equals(existingDish.getRestaurant().getIdOwner()))
            throw new NotBelong("The user is not the owner of the restaurant");

        existingDish.setPrice(dish.getPrice());
        existingDish.setDescription(dish.getDescription());
        return dishPersistencePort.save(existingDish);
    }

    @Override
    public Dish setDishVisibility(Long idDish, Long idRestaurant, boolean visibility, String token) {
        User userOwnerAuthenticated = getUserOwnerAuthenticated(token);
        restaurantBelongsToUser(idRestaurant, userOwnerAuthenticated.getId());
        return dishBelongsDishToRestaurantSavedAndUpdate(idDish, idRestaurant, visibility);
    }

    private User getUserOwnerAuthenticated(String tokenWithPrefixBearer) {
        String emailFromUserAuthenticatedByToken = this.jwtProvider.getAuthentication(tokenWithPrefixBearer.replace("Bearer ", "").trim()).getName();
        return this.gateway.getUserByEmail(emailFromUserAuthenticatedByToken, tokenWithPrefixBearer);
    }

    private void restaurantBelongsToUser(Long idRestaurant, Long idUserOwnerAuthenticated) {
        Restaurant restaurantFound = this.restaurantPersistencePort.findRestaurantById(idRestaurant);
        if (restaurantFound == null) {
            throw new NotFound("Restaurant not found");
        } else if (!restaurantFound.getIdOwner().equals(idUserOwnerAuthenticated)) {
            throw new NotBelong("The restaurant does not belong to the user");
        }
    }

    private Dish dishBelongsDishToRestaurantSavedAndUpdate(Long idDish, Long idRestaurant, boolean active) {
        Dish dishFoundAndUpdateStatus = this.dishPersistencePort.findByIdDish(idDish);
        if (dishFoundAndUpdateStatus == null) {
            throw new NotFound("Dish not found");
        } else if (!dishFoundAndUpdateStatus.getRestaurant().getIdRestaurant().equals(idRestaurant)) {
            throw new NotBelong("The dish does not belong to the restaurant");
        }
        dishFoundAndUpdateStatus.setEnabled(active);
        return this.dishPersistencePort.save(dishFoundAndUpdateStatus);
    }


    @Override
    public RestaurantEfficiencyResponseDto getRestaurantEfficiency(List<Order> orderModelList, String token) {

        return null;
    }


}