package com.pragma.restaurantcrud.domain.exeptions;

public class EmptyFields extends  RuntimeException{
    public EmptyFields(String message) {
        super(message);
    }
}
