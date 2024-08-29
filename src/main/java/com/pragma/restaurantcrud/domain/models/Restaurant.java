package com.pragma.restaurantcrud.domain.models;

public class Restaurant {
    private Long idRestaurant;
    private String name;
    private String address;
    private Long idOwner;
    private String phone;
    private String urlLogo;
    private Long nit;

    public Restaurant() {
    }

    public Restaurant(Long id, String name, String address, Long idOwner, String phone, String urlLogo, Long nit) {
        this.idRestaurant = id;
        this.name = name;
        this.address = address;
        this.idOwner = idOwner;
        this.phone = phone;
        this.urlLogo = urlLogo;
        this.nit = nit;
    }

    public Long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Long id) {
        this.idRestaurant = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public Long getNit() {
        return nit;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }



}
