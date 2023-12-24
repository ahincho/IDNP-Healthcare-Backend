package com.ahincho.healthcare.infrastructure.controllers;

import com.ahincho.healthcare.application.services.CategoryService;
import com.ahincho.healthcare.domain.dtos.CategoryRequest;
import com.ahincho.healthcare.domain.dtos.CategoryResponse;
import com.ahincho.healthcare.domain.entities.CategoryEntity;
import com.ahincho.healthcare.domain.exceptions.CategoryNotFoundException;
import com.ahincho.healthcare.domain.mappers.CategoryMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll() {
        List<CategoryEntity> categoryEntities = categoryService.getAllCategories();
        if (categoryEntities.isEmpty()) { return ResponseEntity.noContent().build(); }
        return ResponseEntity.ok(categoryEntities.stream().map(CategoryMapper::entityToResponse).toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable("id") Integer id) throws CategoryNotFoundException {
        CategoryEntity categoryEntity = categoryService.getCategoryById(id);
        return ResponseEntity.ok(CategoryMapper.entityToResponse(categoryEntity));
    }
    @PostMapping
    public ResponseEntity<CategoryResponse> save(@RequestBody @Valid CategoryRequest categoryRequest, UriComponentsBuilder uriComponentsBuilder) {
        CategoryEntity categoryEntity = categoryService.createCategory(CategoryMapper.requestToEntity(categoryRequest));
        URI uri = uriComponentsBuilder.path("/api/categories/{id}").buildAndExpand(categoryEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(CategoryMapper.entityToResponse(categoryEntity));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable("id") Integer id, @RequestBody @Valid CategoryRequest categoryRequest) throws CategoryNotFoundException {
        categoryService.updateCategory(id, CategoryMapper.requestToEntity(categoryRequest));
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws CategoryNotFoundException {
        categoryService.deleteCategory(id);
        return ResponseEntity.notFound().build();
    }
}
