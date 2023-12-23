package com.ahincho.healthcare.infrastructure.controllers;

import com.ahincho.healthcare.application.services.CategoryService;
import com.ahincho.healthcare.domain.dtos.DrugCategoryRequest;
import com.ahincho.healthcare.domain.dtos.DrugCategoryResponse;
import com.ahincho.healthcare.domain.entities.CategoryEntity;
import com.ahincho.healthcare.domain.exceptions.CategoryNotFoundException;
import com.ahincho.healthcare.domain.mappers.DrugCategoryMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
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
    public ResponseEntity<List<DrugCategoryResponse>> getAll() {
        List<CategoryEntity> drugCategoryEntities = categoryService.getAllDrugCategories();
        if (drugCategoryEntities.isEmpty()) { return ResponseEntity.noContent().build(); }
        return ResponseEntity.ok(drugCategoryEntities.stream().map(DrugCategoryMapper::entityToResponse).toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<DrugCategoryResponse> findById(@PathVariable("id") Integer id) throws CategoryNotFoundException {
        CategoryEntity categoryEntity = categoryService.getDrugCategoryById(id);
        return ResponseEntity.ok(DrugCategoryMapper.entityToResponse(categoryEntity));
    }
    @PostMapping
    public ResponseEntity<DrugCategoryResponse> save(@RequestBody DrugCategoryRequest drugCategoryRequest, UriComponentsBuilder uriComponentsBuilder) {
        CategoryEntity categoryEntity = categoryService.createDrugCategory(DrugCategoryMapper.requestToEntity(drugCategoryRequest));
        URI uri = uriComponentsBuilder.path("/api/categories/{id}").buildAndExpand(categoryEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(DrugCategoryMapper.entityToResponse(categoryEntity));
    }
    @PutMapping("/{id}")
    public ResponseEntity<DrugCategoryResponse> update(@PathVariable("id") Integer id, @RequestBody DrugCategoryRequest drugCategoryRequest) throws CategoryNotFoundException {
        categoryService.updateDrugCategory(id, DrugCategoryMapper.requestToEntity(drugCategoryRequest));
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws CategoryNotFoundException {
        categoryService.deleteDrugCategory(id);
        return ResponseEntity.notFound().build();
    }
}
