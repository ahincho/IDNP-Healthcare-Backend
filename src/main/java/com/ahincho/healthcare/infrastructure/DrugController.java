package com.ahincho.healthcare.infrastructure;

import com.ahincho.healthcare.application.DrugService;
import com.ahincho.healthcare.domain.entities.Drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drug")
public class DrugController {
    @Autowired
    private DrugService drugService;
    @GetMapping
    public List<Drug> getAll() {
        return drugService.getAllDrugs();
    }
    @PostMapping
    public Drug save(@RequestBody Drug drug) {
        return drugService.createDrug(drug);
    }
    @GetMapping("/{id}")
    public Drug findById(@PathVariable("id") Integer id) {
        return drugService.findDrugById(id).orElse(null);
    }
    @PutMapping("/{id}")
    public Drug update(@PathVariable("id") Integer id, @RequestBody Drug drug) {
        return drugService.findDrugById(id)
                .map(savedDrug -> {
                    savedDrug.setName(drug.getName());
                    savedDrug.setDrugCategory(drug.getDrugCategory());
                    savedDrug.setDescription(drug.getDescription());
                    Drug updatedDrug = drugService.updateDrug(savedDrug);
                    return updatedDrug;
                }).orElse(null);
    }
    @DeleteMapping
    public void delete(@PathVariable("id") Integer id) {
        drugService.deleteDrug(id);
    }
}
