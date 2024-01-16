package com.ahincho.healthcare.infrastructure.controllers;

import com.ahincho.healthcare.application.services.CategoryService;
import com.ahincho.healthcare.domain.dtos.categories.CategoryRequest;
import com.ahincho.healthcare.domain.dtos.categories.CategoryResponse;
import com.ahincho.healthcare.domain.entities.CategoryEntity;
import com.ahincho.healthcare.domain.exceptions.categories.CategoryDuplicatedException;
import com.ahincho.healthcare.domain.exceptions.categories.CategoryNotFoundException;
import com.ahincho.healthcare.application.mappers.CategoryMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<CategoryResponse>> getAll() {
        List<CategoryEntity> categoryEntities = categoryService.getAllCategories();
        if (categoryEntities.isEmpty()) { return ResponseEntity.noContent().build(); }
        return ResponseEntity.ok(categoryEntities.stream().map(CategoryMapper::entityToResponse).toList());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<CategoryResponse> findById(@PathVariable("id") Integer id) throws CategoryNotFoundException {
        CategoryEntity categoryEntity = categoryService.getCategoryById(id);
        return ResponseEntity.ok(CategoryMapper.entityToResponse(categoryEntity));
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryResponse> save(@RequestBody @Valid CategoryRequest categoryRequest, UriComponentsBuilder uriComponentsBuilder) {
        CategoryEntity categoryEntity = categoryService.createCategory(CategoryMapper.requestToEntity(categoryRequest));
        URI uri = uriComponentsBuilder.path("/api/categories/{id}").buildAndExpand(categoryEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(CategoryMapper.entityToResponse(categoryEntity));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody @Valid CategoryRequest categoryRequest) throws CategoryNotFoundException, CategoryDuplicatedException {
        categoryService.updateCategory(id, CategoryMapper.requestToEntity(categoryRequest));
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws CategoryNotFoundException {
        categoryService.deleteCategory(id);
        return ResponseEntity.notFound().build();
    }
}
