package com.pragma.restaurantcrud.domain.models;

import java.util.Date;
import java.util.List;


public class Order {

    private  Long idOrder;
    private  Long idCustomer;
    private Date date;
    private  String status;
   private  EmployeeRestaurant idEmployeeRestaurant;
    private List<OrderDish> orderDishes;
    private Restaurant idRestaurant;

    public Order() {
    }

    public Order(Long idOrder, Long idCustomer, Date date, String status, EmployeeRestaurant idEmployeeRestaurant, List<OrderDish> orderDishes, Restaurant idRestaurant) {
        this.idOrder = idOrder;
        this.idCustomer = idCustomer;
        this.date = date;
        this.status = status;
        this.idEmployeeRestaurant = idEmployeeRestaurant;
        this.orderDishes = orderDishes;
        this.idRestaurant = idRestaurant;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EmployeeRestaurant getIdEmployeeRestaurant() {
        return idEmployeeRestaurant;
    }

    public void setIdEmployeeRestaurant(EmployeeRestaurant idEmployeeRestaurant) {
        this.idEmployeeRestaurant = idEmployeeRestaurant;
    }

    public List<OrderDish> getOrderDishes() {
        return orderDishes;
    }

    public void setOrderDishes(List<OrderDish> orderDishes) {
        this.orderDishes = orderDishes;
    }

    public Restaurant getRestaurant() {
        return idRestaurant;
    }

    public void setRestaurant(Restaurant idRestaurant) {
        this.idRestaurant = idRestaurant;
    }
}
