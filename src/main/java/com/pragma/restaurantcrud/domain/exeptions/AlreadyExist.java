package com.pragma.restaurantcrud.domain.exeptions;

public class AlreadyExist extends  RuntimeException{
    public AlreadyExist(String message) {
        super(message);
    }
}
