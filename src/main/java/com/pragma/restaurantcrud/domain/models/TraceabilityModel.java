package com.pragma.restaurantcrud.domain.models;

import java.time.LocalDateTime;

public class TraceabilityModel {

    private String id;
    private Integer orderId;
    private Integer customerId;
    private String customerEmail;
    private String currentStatus;
    private Integer employeeId;
    private String employeeEmail;
    private LocalDateTime orderStartDate;
    private LocalDateTime orderEndDate;


    public TraceabilityModel(String id, Integer orderId, Integer customerId, String customerEmail, String currentStatus, Integer employeeId, String employeeEmail, LocalDateTime orderStartDate, LocalDateTime orderEndDate) {
        this.id = id;
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerEmail = customerEmail;
        this.currentStatus = currentStatus;
        this.employeeId = employeeId;
        this.employeeEmail = employeeEmail;
        this.orderStartDate = orderStartDate;
        this.orderEndDate = orderEndDate;
    }


    public TraceabilityModel() {
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public Integer getOrderId() {
        return orderId;
    }


    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


    public Integer getCustomerId() {
        return customerId;
    }


    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }


    public String getCustomerEmail() {
        return customerEmail;
    }


    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }


    public String getCurrentStatus() {
        return currentStatus;
    }


    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }


    public Integer getEmployeeId() {
        return employeeId;
    }


    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }


    public String getEmployeeEmail() {
        return employeeEmail;
    }


    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }


    public LocalDateTime getOrderStartDate() {
        return orderStartDate;
    }


    public void setOrderStartDate(LocalDateTime orderStartDate) {
        this.orderStartDate = orderStartDate;
    }


    public LocalDateTime getOrderEndDate() {
        return orderEndDate;
    }


    public void setOrderEndDate(LocalDateTime orderEndDate) {
        this.orderEndDate = orderEndDate;
    }


}
