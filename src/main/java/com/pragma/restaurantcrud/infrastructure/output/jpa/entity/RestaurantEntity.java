package com.pragma.restaurantcrud.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "restaurants")
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRestaurant")
    private Long idRestaurant;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "idOwner")
    private Long idOwner;
    @Column(name = "phone")
    private String phone;
    @Column(name = "urlLogo")
    private String urlLogo;
    @Column(name = "nit")
    private Long  nit;

    @OneToMany(mappedBy = "restaurant")
    private List<DishEntity> dishes;

}
