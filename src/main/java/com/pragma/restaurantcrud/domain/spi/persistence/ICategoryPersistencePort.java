package com.pragma.restaurantcrud.domain.spi.persistence;

import com.pragma.restaurantcrud.domain.models.Category;

public interface ICategoryPersistencePort {

    Category findByIdCategory(Long idCategory);
}
