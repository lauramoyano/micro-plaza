package com.pragma.restaurantcrud.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;
    @Column(name = "idCustomer")
    private Long idCustomer;
    @Column(name = "date")
    private Date date;
    @Column(name = "status")
    private String status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmployeeRestaurant", referencedColumnName = "idEmployeeRestaurant")
    private EmployeeRestaurantEntity employeeRestaurant;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRestaurant", referencedColumnName = "idRestaurant")
    private RestaurantEntity restaurant;

    @OneToMany(mappedBy = "orderEntity", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<OrderDishEntity> orderDishEntities;
}
