package com.ahincho.healthcare.infrastructure;

import com.ahincho.healthcare.application.services.DrugService;
import com.ahincho.healthcare.domain.entities.DrugEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drugs")
public class DrugController {
    @Autowired
    private DrugService drugService;
    @GetMapping
    public List<DrugEntity> getAll() {
        return drugService.getAllDrugs();
    }
    @GetMapping("/category/{category}")
    public List<DrugEntity> getByCategory(@PathVariable("category") Integer category) {
        return drugService.getDrugsByCategoryId(category);
    }
    @PostMapping
    public DrugEntity save(@RequestBody DrugEntity drugEntity) {
        return drugService.createDrug(drugEntity);
    }
    @GetMapping("/{id}")
    public DrugEntity findById(@PathVariable("id") Integer id) {
        return drugService.findDrugById(id).orElse(null);
    }
    @PutMapping("/{id}")
    public DrugEntity update(@PathVariable("id") Integer id, @RequestBody DrugEntity drugEntity) {
        return drugService.findDrugById(id)
                .map(savedDrug -> {
                    savedDrug.setName(drugEntity.getName());
                    savedDrug.setDrugCategory(drugEntity.getDrugCategory());
                    savedDrug.setDescription(drugEntity.getDescription());
                    return drugService.updateDrug(savedDrug);
                }).orElse(null);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        drugService.deleteDrug(id);
    }
}
