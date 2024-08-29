package com.pragma.restaurantcrud.infrastructure.output.jpa.mapper;

import com.pragma.restaurantcrud.domain.models.Category;
import com.pragma.restaurantcrud.infrastructure.output.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryMapper {
    CategoryEntity mapCategoryToCategoryEntity(Category category);
    Category mapCategoryEntityToCategory(CategoryEntity categoryEntity);
}
