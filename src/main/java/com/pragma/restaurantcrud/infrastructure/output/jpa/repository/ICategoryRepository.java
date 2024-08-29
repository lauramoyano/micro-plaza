package com.pragma.restaurantcrud.infrastructure.output.jpa.repository;

import com.pragma.restaurantcrud.infrastructure.output.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByName(String name);
    CategoryEntity findCategoryByIdCategory(Long id);
    boolean existsByIdCategory(Long id);
}
