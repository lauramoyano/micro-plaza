package com.pragma.restaurantcrud.infrastructure.output.jpa.repository;

import com.pragma.restaurantcrud.infrastructure.output.jpa.entity.EmployeeRestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRestaurantRepository extends JpaRepository<EmployeeRestaurantEntity, Long> {

    EmployeeRestaurantEntity findByIdEmployee(Long idEmployee);

    Page<EmployeeRestaurantEntity> findAllByIdRestaurant(Long idRestaurant, Pageable pageable);

    Long findIdRestaurantByIdEmployee(Long idEmployee);
}
