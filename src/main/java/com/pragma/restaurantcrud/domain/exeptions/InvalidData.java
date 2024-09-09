package com.pragma.restaurantcrud.domain.exeptions;

public class InvalidData extends  RuntimeException{
    public InvalidData(String message) {
        super(message);
    }

}
