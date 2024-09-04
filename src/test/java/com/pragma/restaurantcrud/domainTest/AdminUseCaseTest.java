package com.pragma.restaurantcrud.domainTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


import com.pragma.restaurantcrud.domain.models.Restaurant;
import com.pragma.restaurantcrud.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.restaurantcrud.domain.spi.servicePortClient.IGateway;
import com.pragma.restaurantcrud.domain.usecase.AdminUsecase;
import com.pragma.restaurantcrud.infrastructure.output.client.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

    @ExtendWith(MockitoExtension.class)
    class AdminUsecaseTest {

        @InjectMocks
        private AdminUsecase adminUsecase;

        @Mock
        private IRestaurantPersistencePort restaurantPersistencePort;

        @Mock
        private IGateway gateway;

        @Test
        void testCreateRestaurantSuccess() {
            Restaurant restaurant = new Restaurant();
            restaurant.setName("Test Restaurant");
            restaurant.setIdOwner(1L);
            restaurant.setPhone("+1234567890");
            restaurant.setNit(123456L);
            restaurant.setAddress("123 Test St");
            restaurant.setUrlLogo("http://testlogo.com");

            UserDto userDto = new UserDto();
            userDto.setRolName("OWNER");

            when(restaurantPersistencePort.existsRestaurantByName("Test Restaurant")).thenReturn(false);
            when(gateway.getUserById(1L, "token")).thenReturn(userDto);
            when(restaurantPersistencePort.saveRestaurant(restaurant)).thenReturn(restaurant);

            Restaurant result = adminUsecase.createRestaurant(restaurant, "token");

            assertEquals(restaurant.getName(), result.getName());
            verify(restaurantPersistencePort).saveRestaurant(restaurant);
        }

        @Test
        void testCreateRestaurantAlreadyExists() {
            Restaurant restaurant = new Restaurant();
            restaurant.setName("Existing Restaurant");

            when(restaurantPersistencePort.existsRestaurantByName("Existing Restaurant")).thenReturn(true);

            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                adminUsecase.createRestaurant(restaurant, "token");
            });

            assertEquals("Restaurant already exists", exception.getMessage());
        }
        @Test
        void testValidateRestaurantFieldsMissingName() {
            Restaurant restaurant = new Restaurant();
            restaurant.setName(null);  // Missing Name
            restaurant.setAddress("123 Test St");
            restaurant.setIdOwner(1L);
            restaurant.setPhone("+1234567890");
            restaurant.setNit(123456L);
            restaurant.setUrlLogo("http://testlogo.com");

            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                adminUsecase.createRestaurant(restaurant, "token");
            });

            assertEquals("Name is required", exception.getMessage()); // Ajusta el mensaje esperado
        }

        @Test
        void testValidateNitInvalid() {
            Restaurant restaurant = new Restaurant();
            restaurant.setName("Valid Name");
            restaurant.setAddress("123 Test St");
            restaurant.setIdOwner(1L);
            restaurant.setPhone("+1234567890");
            restaurant.setNit(null);  // Invalid NIT
            restaurant.setUrlLogo("http://testlogo.com");

            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                adminUsecase.createRestaurant(restaurant, "token");
            });

            assertEquals("Nit is required", exception.getMessage()); // Ajusta el mensaje esperado
        }




        @Test
        void testValidateRestaurantNameInvalid() {
            Restaurant restaurant = new Restaurant();
            restaurant.setName("1234");
            restaurant.setAddress("123 Test St");
            restaurant.setIdOwner(1L);
            restaurant.setPhone("+1234567890");
            restaurant.setNit(123456L);
            restaurant.setUrlLogo("http://testlogo.com");

            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                adminUsecase.createRestaurant(restaurant, "token");
            });

            assertEquals("Restaurant name cannot contain only numbers", exception.getMessage());
        }

        @Test
        void testValidatePhoneInvalid() {
            Restaurant restaurant = new Restaurant();
            restaurant.setName("Valid Name");
            restaurant.setAddress("123 Test St");
            restaurant.setIdOwner(1L);
            restaurant.setPhone("invalidPhone");
            restaurant.setNit(123456L);
            restaurant.setUrlLogo("http://testlogo.com");

            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                adminUsecase.createRestaurant(restaurant, "token");
            });

            assertEquals("Phone must be a maximum of 13 characters and can contain the symbol +", exception.getMessage());
        }





    }
