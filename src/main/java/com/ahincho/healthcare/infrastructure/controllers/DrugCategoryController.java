package com.ahincho.healthcare.infrastructure.controllers;

import com.ahincho.healthcare.application.services.DrugCategoryService;
import com.ahincho.healthcare.domain.dtos.DrugCategoryRequest;
import com.ahincho.healthcare.domain.dtos.DrugCategoryResponse;
import com.ahincho.healthcare.domain.entities.DrugCategoryEntity;
import com.ahincho.healthcare.domain.exceptions.DrugCategoryNotFoundException;
import com.ahincho.healthcare.domain.mappers.DrugCategoryMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class DrugCategoryController {
    private final DrugCategoryService drugCategoryService;
    public DrugCategoryController(DrugCategoryService drugCategoryService) {
        this.drugCategoryService = drugCategoryService;
    }
    @GetMapping
    public ResponseEntity<List<DrugCategoryResponse>> getAll() {
        List<DrugCategoryEntity> drugCategoryEntities = drugCategoryService.getAllDrugCategories();
        if (drugCategoryEntities.isEmpty()) { return ResponseEntity.noContent().build(); }
        return ResponseEntity.ok(drugCategoryEntities.stream().map(DrugCategoryMapper::entityToResponse).toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<DrugCategoryResponse> findById(@PathVariable("id") Integer id) throws DrugCategoryNotFoundException {
        DrugCategoryEntity drugCategoryEntity = drugCategoryService.getDrugCategoryById(id);
        return ResponseEntity.ok(DrugCategoryMapper.entityToResponse(drugCategoryEntity));
    }
    @PostMapping
    public ResponseEntity<DrugCategoryResponse> save(@RequestBody DrugCategoryRequest drugCategoryRequest, UriComponentsBuilder uriComponentsBuilder) {
        DrugCategoryEntity drugCategoryEntity = drugCategoryService.createDrugCategory(DrugCategoryMapper.requestToEntity(drugCategoryRequest));
        URI uri = uriComponentsBuilder.path("/api/categories/{id}").buildAndExpand(drugCategoryEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(DrugCategoryMapper.entityToResponse(drugCategoryEntity));
    }
    @PutMapping("/{id}")
    public ResponseEntity<DrugCategoryResponse> update(@PathVariable("id") Integer id, @RequestBody DrugCategoryRequest drugCategoryRequest) throws DrugCategoryNotFoundException {
        drugCategoryService.updateDrugCategory(id, DrugCategoryMapper.requestToEntity(drugCategoryRequest));
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws DrugCategoryNotFoundException {
        drugCategoryService.deleteDrugCategory(id);
        return ResponseEntity.notFound().build();
    }
}
