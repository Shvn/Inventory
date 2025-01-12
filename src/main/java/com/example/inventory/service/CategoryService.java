package com.example.inventory.service;

import com.example.inventory.repository.CategoryRepository;
import com.example.inventory.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryEntity createCategory(CategoryEntity category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new IllegalArgumentException("Category name must be unique");
        }
        return categoryRepository.save(category);
    }

    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    public CategoryEntity getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public CategoryEntity updateCategory(Long id, CategoryEntity category) {
        CategoryEntity existingCategory = getCategoryById(id);
        existingCategory.setName(category.getName());
        return categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}

