package com.pragma.restaurantcrud.domain.models;

public class Category {

    private Long idCategory;
    private String name;
    private String description;

    public Category() {
    }

    public Category(Long id, String name, String description) {
        this.idCategory = id;
        this.name = name;
        this.description = description;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long id) {
        this.idCategory = id;
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
}
