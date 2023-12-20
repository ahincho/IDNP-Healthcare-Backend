package com.ahincho.healthcare.infrastructure;

import com.ahincho.healthcare.application.DrugCategoryService;
import com.ahincho.healthcare.domain.entities.DrugCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drug/category")
public class DrugCategoryController {
    @Autowired
    private DrugCategoryService drugCategoryService;
    @GetMapping
    public List<DrugCategory> getAll() {
        return drugCategoryService.getAllDrugCategories();
    }
    @PostMapping
    public DrugCategory save(@RequestBody DrugCategory drugCategory) {
        return drugCategoryService.createDrugCategory(drugCategory);
    }
    @GetMapping("/{id}")
    public DrugCategory findById(@PathVariable("id") Integer id) {
        return drugCategoryService.getDrugCategoryById(id).orElse(null);
    }
    @PutMapping("/{id}")
    public DrugCategory update(@PathVariable("id") Integer id, @RequestBody DrugCategory drugCategory) {
        return drugCategoryService.getDrugCategoryById(id)
                .map(savedDrugCategory -> {
                    savedDrugCategory.setName(drugCategory.getName());
                    DrugCategory updatedDrugCategory = drugCategoryService.updateDrugCategory(savedDrugCategory);
                    return updatedDrugCategory;
                }).orElse(null);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        drugCategoryService.deleteDrugCategory(id);
    }
}
