package com.pragma.restaurantcrud.infrastructure.output.jpa.adapter;

import com.pragma.restaurantcrud.domain.models.Category;
import com.pragma.restaurantcrud.domain.spi.persistence.ICategoryPersistencePort;
import com.pragma.restaurantcrud.infrastructure.output.jpa.mapper.ICategoryMapper;
import com.pragma.restaurantcrud.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryMapper categoryMapper;

    @Override
    public Category findByIdCategory(Long id) {
        return categoryMapper.mapCategoryEntityToCategory(categoryRepository.findCategoryByIdCategory(id));
    }
}
