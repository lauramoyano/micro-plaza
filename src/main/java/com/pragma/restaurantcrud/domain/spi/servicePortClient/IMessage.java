package com.pragma.restaurantcrud.domain.spi.servicePortClient;

import com.pragma.restaurantcrud.domain.models.PinMessage;

public interface IMessage {
    void sendNotification(PinMessage pinMessage, String token);
}
