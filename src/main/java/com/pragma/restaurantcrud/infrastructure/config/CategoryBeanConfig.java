package com.pragma.restaurantcrud.infrastructure.config;

import com.pragma.restaurantcrud.domain.spi.persistence.ICategoryPersistencePort;
import com.pragma.restaurantcrud.infrastructure.output.jpa.adapter.CategoryAdapter;
import com.pragma.restaurantcrud.infrastructure.output.jpa.mapper.ICategoryMapper;
import com.pragma.restaurantcrud.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class CategoryBeanConfig {

        private final ICategoryRepository categoryRepository;
        private final ICategoryMapper categoryEntityMapper;

        @Bean
        public ICategoryPersistencePort categoryPersistencePort() {
            return new CategoryAdapter(categoryRepository, categoryEntityMapper );
        }
}
