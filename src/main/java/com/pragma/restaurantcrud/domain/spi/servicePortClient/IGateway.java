package com.pragma.restaurantcrud.domain.spi.servicePortClient;

import com.pragma.restaurantcrud.infrastructure.output.client.UserDto;
import com.pragma.restaurantcrud.domain.models.User;

public interface IGateway {
    UserDto getUserById(Long id, String token);
    User getUserByEmail(String email, String token);
}
