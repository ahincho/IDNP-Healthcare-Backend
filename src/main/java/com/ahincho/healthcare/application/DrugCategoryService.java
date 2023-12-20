package com.ahincho.healthcare.application;

import com.ahincho.healthcare.domain.entities.DrugCategory;
import com.ahincho.healthcare.domain.repositories.DrugCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DrugCategoryService {
    @Autowired
    private DrugCategoryRepository drugCategoryRepository;
    public List<DrugCategory> getAllDrugCategories() {
        return drugCategoryRepository.findAll();
    }
    public DrugCategory createDrugCategory(DrugCategory drugCategory) {
        return drugCategoryRepository.save(drugCategory);
    }
    public Optional<DrugCategory> getDrugCategoryById(Integer id) {
        return drugCategoryRepository.findById(id);
    }
    public DrugCategory updateDrugCategory(DrugCategory drugCategory) {
        return drugCategoryRepository.save(drugCategory);
    }
    public void deleteDrugCategory(Integer id) {
        drugCategoryRepository.deleteById(id);
    }
}
