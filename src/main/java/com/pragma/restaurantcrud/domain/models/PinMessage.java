package com.pragma.restaurantcrud.domain.models;

public class PinMessage {
    private Long pin;
    private String nameCustomer;
    private String phoneNumber;
    private String restaurantName;
    private String message;


    public PinMessage() {
    }

    public PinMessage(Long pin, String nameCustomer, String phoneNumber, String restaurantName, String message) {
        this.pin = pin;
        this.nameCustomer = nameCustomer;
        this.phoneNumber = phoneNumber;
        this.restaurantName = restaurantName;
        this.message = message;
    }

    public Long getPin() {
        return pin;
    }

    public void setPin(Long pin) {
        this.pin = pin;
    }


    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
