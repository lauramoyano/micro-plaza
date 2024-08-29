package com.pragma.restaurantcrud.domain.models;

public class OrderDish {

    private Long idOrderDish;
    private Order idOrder;
    private Dish idDish;
    private Integer quantity;

    public OrderDish() {
    }

    public OrderDish(Long idOrderDish, Order idOrder, Dish idDish, Integer quantity) {
        this.idOrderDish = idOrderDish;
        this.idOrder = idOrder;
        this.idDish = idDish;
        this.quantity = quantity;

    }

    public Long getIdOrderDish() {
        return idOrderDish;
    }

    public void setIdOrderDish(Long idOrderDish) {
        this.idOrderDish = idOrderDish;
    }

    public Order getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Order idOrder) {
        this.idOrder = idOrder;
    }

    public Dish getIdDish() {
        return idDish;
    }

    public void setIdDish(Dish idDish) {
        this.idDish = idDish;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
