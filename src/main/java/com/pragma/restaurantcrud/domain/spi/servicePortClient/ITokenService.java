package com.pragma.restaurantcrud.domain.spi.servicePortClient;

public interface ITokenService {
    String getEmailFromToken(String token);
    String getTokenPrefix();
}
