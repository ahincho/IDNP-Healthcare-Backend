package com.ahincho.healthcare.application.services;

import com.ahincho.healthcare.domain.entities.CategoryEntity;
import com.ahincho.healthcare.domain.exceptions.CategoryDuplicatedException;
import com.ahincho.healthcare.domain.exceptions.CategoryNotFoundException;
import com.ahincho.healthcare.domain.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public List<CategoryEntity> getAllDrugCategories() {
        return categoryRepository.findAll();
    }
    public CategoryEntity createDrugCategory(CategoryEntity drugCategory) {
        Optional<CategoryEntity> optionalDrugCategory = categoryRepository.findByName(drugCategory.getName());
        if (optionalDrugCategory.isPresent()) { throw new CategoryDuplicatedException(); }
        return categoryRepository.save(drugCategory);
    }
    public CategoryEntity getDrugCategoryById(Integer id) throws CategoryNotFoundException {
        Optional<CategoryEntity> optionalDrugCategory = categoryRepository.findById(id);
        if (optionalDrugCategory.isEmpty()) { throw new CategoryNotFoundException(); }
        return optionalDrugCategory.get();
    }
    public CategoryEntity updateDrugCategory(Integer id, CategoryEntity categoryEntity) throws CategoryNotFoundException {
        Optional<CategoryEntity> optionalDrugCategory = categoryRepository.findById(id);
        if (optionalDrugCategory.isEmpty()) { throw new CategoryNotFoundException(); }
        CategoryEntity drugCategory = optionalDrugCategory.get();
        drugCategory.setId(id);
        return categoryRepository.save(drugCategory);
    }
    public void deleteDrugCategory(Integer id) throws CategoryNotFoundException {
        Optional<CategoryEntity> optionalDrugCategory = categoryRepository.findById(id);
        if (optionalDrugCategory.isEmpty()) { throw new CategoryNotFoundException(); }
        categoryRepository.deleteById(id);
    }
}
