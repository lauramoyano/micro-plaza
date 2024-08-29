package com.pragma.restaurantcrud.infrastructure.config.securityClient;

public interface ITokenUser {
    boolean isValidToken(String token);
}
