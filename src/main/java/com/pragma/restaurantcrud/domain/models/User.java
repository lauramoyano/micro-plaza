package com.pragma.restaurantcrud.domain.models;

public class User {
    private Long idUser;
    private String name;
    private String lastName;
    private String email;
    private String role;

    public User() {
    }

    public User(Long id, String name, String lastName,String email, String role) {
        this.idUser = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long id) {
        this.idUser = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

        public String getLastName() {
            return lastName;
        }
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
