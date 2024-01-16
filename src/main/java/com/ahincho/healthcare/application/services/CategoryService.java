package com.ahincho.healthcare.application.services;

import com.ahincho.healthcare.domain.entities.CategoryEntity;
import com.ahincho.healthcare.domain.exceptions.categories.CategoryDuplicatedException;
import com.ahincho.healthcare.domain.exceptions.categories.CategoryNotFoundException;
import com.ahincho.healthcare.domain.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }
    @Transactional
    public CategoryEntity createCategory(CategoryEntity categoryEntity) {
        Optional<CategoryEntity> optionalCategory = categoryRepository.findByName(categoryEntity.getName());
        if (optionalCategory.isPresent()) { throw new CategoryDuplicatedException(); }
        return categoryRepository.save(categoryEntity);
    }
    public CategoryEntity getCategoryById(Integer id) throws CategoryNotFoundException {
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) { throw new CategoryNotFoundException(); }
        return optionalCategory.get();
    }
    @Transactional
    public void updateCategory(Integer id, CategoryEntity categoryEntity) throws CategoryNotFoundException, CategoryDuplicatedException {
        Optional<CategoryEntity> optionalCategoryId = categoryRepository.findById(id);
        if (optionalCategoryId.isEmpty()) { throw new CategoryNotFoundException(); }
        Optional<CategoryEntity> optionalCategoryName = categoryRepository.findByName(categoryEntity.getName());
        if (optionalCategoryName.isPresent()) { throw new CategoryDuplicatedException(); }
        categoryEntity.setId(id);
        categoryRepository.save(categoryEntity);
    }
    @Transactional
    public void deleteCategory(Integer id) throws CategoryNotFoundException {
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) { throw new CategoryNotFoundException(); }
        categoryRepository.deleteById(id);
    }
}
