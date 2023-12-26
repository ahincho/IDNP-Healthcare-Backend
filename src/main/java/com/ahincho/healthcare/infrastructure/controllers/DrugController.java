package com.ahincho.healthcare.infrastructure.controllers;

import com.ahincho.healthcare.application.services.DrugService;
import com.ahincho.healthcare.domain.dtos.DrugRequest;
import com.ahincho.healthcare.domain.dtos.DrugResponse;
import com.ahincho.healthcare.domain.entities.DrugEntity;
import com.ahincho.healthcare.domain.exceptions.CategoryNotFoundException;
import com.ahincho.healthcare.domain.exceptions.DrugDuplicatedException;
import com.ahincho.healthcare.domain.exceptions.DrugNotFoundException;
import com.ahincho.healthcare.domain.mappers.DrugMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/drugs")
public class DrugController {
    private final DrugService drugService;
    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<DrugResponse>> getAll() {
        List<DrugEntity> drugEntities = drugService.getAllDrugs();
        if (drugEntities.isEmpty()) { return ResponseEntity.noContent().build(); }
        return ResponseEntity.ok(drugEntities.stream().map(DrugMapper::entityToResponse).toList());
    }
    @GetMapping("/category/{category}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<DrugResponse>> getByCategory(@PathVariable("category") Integer category) {
        List<DrugEntity> drugEntities = drugService.getDrugsByCategoryId(category);
        if (drugEntities.isEmpty()) { return ResponseEntity.noContent().build(); }
        return ResponseEntity.ok(drugEntities.stream().map(DrugMapper::entityToResponse).toList());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<DrugResponse> findById(@PathVariable("id") Integer id) throws DrugNotFoundException {
        DrugEntity drugEntity = drugService.findDrugById(id);
        return ResponseEntity.ok(DrugMapper.entityToResponse(drugEntity));
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DrugResponse> save(@RequestBody @Valid DrugRequest drugRequest, UriComponentsBuilder uriComponentsBuilder) throws CategoryNotFoundException, DrugDuplicatedException {
        DrugEntity drugEntity = drugService.createDrug(DrugMapper.requestToEntity(drugRequest));
        URI uri = uriComponentsBuilder.path("/api/drugs/{id}").buildAndExpand(drugEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(DrugMapper.entityToResponse(drugEntity));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody @Valid DrugRequest drugRequest) throws CategoryNotFoundException, DrugNotFoundException, DrugDuplicatedException {
        drugService.updateDrug(id, DrugMapper.requestToEntity(drugRequest));
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws DrugNotFoundException {
        drugService.deleteDrug(id);
        return ResponseEntity.notFound().build();
    }
}
