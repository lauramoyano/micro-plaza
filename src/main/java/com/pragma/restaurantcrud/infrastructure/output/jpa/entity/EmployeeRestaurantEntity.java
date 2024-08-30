package com.pragma.restaurantcrud.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "employee_restaurant")
public class EmployeeRestaurantEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long idEmployeeRestaurant;
    @Column(name = "idEmployee")
    private Long idEmployee;
    @Column(name = "idRestaurant")
    private Long idRestaurant;
}
