package com.pragma.restaurantcrud.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
}
