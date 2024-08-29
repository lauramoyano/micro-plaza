package com.pragma.restaurantcrud.domain.models;

public class Dish {

    private Long idDish;
    private String name;
    private String description;
    private Double price;
    private Category idCategory;
    private String urlImage;
    private Restaurant idRestaurant;
    private Boolean enabled;

    public Dish() {
    }

    public Dish(Long idDish, String name, String description, Double price, Category idCategory, String urlImage, Restaurant idRestaurant, Boolean enabled) {
        this.idDish = idDish;
        this.name = name;
        this.description = description;
        this.price = price;
        this.idCategory = idCategory;
        this.urlImage = urlImage;
        this.idRestaurant = idRestaurant;
        this.enabled = enabled;
    }

    public Long getIdDish() {
        return idDish;
    }

    public void setIdDish(Long id) {
        this.idDish = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return idCategory;
    }

    public void setCategory(Category idCategory) {
        this.idCategory = idCategory;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Restaurant getRestaurant() {
        return idRestaurant;
    }


    public void setRestaurant(Restaurant idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
