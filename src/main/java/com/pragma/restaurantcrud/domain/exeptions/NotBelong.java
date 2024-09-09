package com.pragma.restaurantcrud.domain.exeptions;

public class NotBelong  extends  RuntimeException{
    public NotBelong(String message) {
        super(message);
    }
}
