package com.pragma.restaurantcrud.domain.models;

public class EmployeeRestaurant {
    private Long idEmployeeRestaurant;
    private Long idEmployee;
    private Long idRestaurant;

    public EmployeeRestaurant() {
    }

    public EmployeeRestaurant(Long idEmployeeRestaurant, Long idEmployee, Long idRestaurant) {
        this.idEmployeeRestaurant = idEmployeeRestaurant;
        this.idEmployee = idEmployee;
        this.idRestaurant = idRestaurant;
    }

    public Long getIdEmployeeRestaurant() {
        return idEmployeeRestaurant;
    }

    public void setIdEmployeeRestaurant(Long idEmployeeRestaurant) {
        this.idEmployeeRestaurant = idEmployeeRestaurant;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }
}
