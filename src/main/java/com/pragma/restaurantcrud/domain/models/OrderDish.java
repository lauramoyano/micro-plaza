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

    public Order getOrder() {
        return idOrder;
    }

    public void setOrder(Order idOrder) {
        this.idOrder = idOrder;
    }

    public Dish getDish() {
        return idDish;
    }

    public void setDish(Dish idDish) {
        this.idDish = idDish;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
