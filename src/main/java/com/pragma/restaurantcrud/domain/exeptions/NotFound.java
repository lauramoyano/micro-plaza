package com.pragma.restaurantcrud.domain.exeptions;

public class NotFound extends  RuntimeException{
    public NotFound(String message) {
        super(message);
    }
}
