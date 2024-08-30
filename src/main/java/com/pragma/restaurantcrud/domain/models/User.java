package com.pragma.restaurantcrud.domain.models;

public class User {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String rolName;

    public User() {
    }

    public User(Long id, String name, String lastName,String email, String rolName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.rolName = rolName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = this.rolName;
    }
}
