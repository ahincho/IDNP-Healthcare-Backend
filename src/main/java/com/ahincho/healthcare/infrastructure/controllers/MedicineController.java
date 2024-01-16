package com.ahincho.healthcare.infrastructure.controllers;

import com.ahincho.healthcare.application.services.MedicineService;
import com.ahincho.healthcare.domain.dtos.medicines.MedicineRequest;
import com.ahincho.healthcare.domain.dtos.medicines.MedicineResponse;
import com.ahincho.healthcare.domain.entities.MedicineEntity;
import com.ahincho.healthcare.domain.exceptions.categories.CategoryNotFoundException;
import com.ahincho.healthcare.domain.exceptions.medicines.MedicineDuplicatedException;
import com.ahincho.healthcare.domain.exceptions.medicines.MedicineNotFoundException;
import com.ahincho.healthcare.application.mappers.MedicineMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {
    private final MedicineService medicineService;
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<MedicineResponse>> getAll() {
        List<MedicineEntity> drugEntities = medicineService.getAllDrugs();
        if (drugEntities.isEmpty()) { return ResponseEntity.noContent().build(); }
        return ResponseEntity.ok(drugEntities.stream().map(MedicineMapper::entityToResponse).toList());
    }
    @GetMapping("/category/{category}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<MedicineResponse>> getByCategory(@PathVariable("category") Integer category) {
        List<MedicineEntity> drugEntities = medicineService.getDrugsByCategoryId(category);
        if (drugEntities.isEmpty()) { return ResponseEntity.noContent().build(); }
        return ResponseEntity.ok(drugEntities.stream().map(MedicineMapper::entityToResponse).toList());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<MedicineResponse> findById(@PathVariable("id") Integer id) throws MedicineNotFoundException {
        MedicineEntity medicineEntity = medicineService.findDrugById(id);
        return ResponseEntity.ok(MedicineMapper.entityToResponse(medicineEntity));
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MedicineResponse> save(@RequestBody @Valid MedicineRequest medicineRequest, UriComponentsBuilder uriComponentsBuilder) throws CategoryNotFoundException, MedicineDuplicatedException {
        MedicineEntity medicineEntity = medicineService.createDrug(MedicineMapper.requestToEntity(medicineRequest));
        URI uri = uriComponentsBuilder.path("/api/drugs/{id}").buildAndExpand(medicineEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(MedicineMapper.entityToResponse(medicineEntity));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody @Valid MedicineRequest medicineRequest) throws CategoryNotFoundException, MedicineNotFoundException, MedicineDuplicatedException {
        medicineService.updateDrug(id, MedicineMapper.requestToEntity(medicineRequest));
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws MedicineNotFoundException {
        medicineService.deleteDrug(id);
        return ResponseEntity.notFound().build();
    }
}
