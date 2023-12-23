package com.ahincho.healthcare.infrastructure;

import com.ahincho.healthcare.application.services.DrugCategoryService;
import com.ahincho.healthcare.domain.entities.DrugCategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class DrugCategoryController {
    @Autowired
    private DrugCategoryService drugCategoryService;
    @GetMapping
    public List<DrugCategoryEntity> getAll() {
        return drugCategoryService.getAllDrugCategories();
    }
    @PostMapping
    public DrugCategoryEntity save(@RequestBody DrugCategoryEntity drugCategoryEntity) {
        return drugCategoryService.createDrugCategory(drugCategoryEntity);
    }
    @GetMapping("/{id}")
    public DrugCategoryEntity findById(@PathVariable("id") Integer id) {
        return drugCategoryService.getDrugCategoryById(id).orElse(null);
    }
    @PutMapping("/{id}")
    public DrugCategoryEntity update(@PathVariable("id") Integer id, @RequestBody DrugCategoryEntity drugCategoryEntity) {
        return drugCategoryService.getDrugCategoryById(id)
                .map(savedDrugCategory -> {
                    savedDrugCategory.setName(drugCategoryEntity.getName());
                    return drugCategoryService.updateDrugCategory(savedDrugCategory);
                }).orElse(null);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        drugCategoryService.deleteDrugCategory(id);
    }
}
