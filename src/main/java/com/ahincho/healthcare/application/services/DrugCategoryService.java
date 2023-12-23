package com.ahincho.healthcare.application.services;

import com.ahincho.healthcare.domain.entities.DrugCategoryEntity;
import com.ahincho.healthcare.domain.exceptions.DrugCategoryDuplicatedException;
import com.ahincho.healthcare.domain.exceptions.DrugCategoryNotFoundException;
import com.ahincho.healthcare.domain.repositories.DrugCategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DrugCategoryService {
    private final DrugCategoryRepository drugCategoryRepository;
    public DrugCategoryService(DrugCategoryRepository drugCategoryRepository) {
        this.drugCategoryRepository = drugCategoryRepository;
    }
    public List<DrugCategoryEntity> getAllDrugCategories() {
        return drugCategoryRepository.findAll();
    }
    public DrugCategoryEntity createDrugCategory(DrugCategoryEntity drugCategory) {
        Optional<DrugCategoryEntity> optionalDrugCategory = drugCategoryRepository.findByName(drugCategory.getName());
        if (optionalDrugCategory.isPresent()) { throw new DrugCategoryDuplicatedException(); }
        return drugCategoryRepository.save(drugCategory);
    }
    public DrugCategoryEntity getDrugCategoryById(Integer id) {
        Optional<DrugCategoryEntity> optionalDrugCategory = drugCategoryRepository.findById(id);
        if (optionalDrugCategory.isEmpty()) { throw new DrugCategoryNotFoundException(); }
        return optionalDrugCategory.get();
    }
    public DrugCategoryEntity updateDrugCategory(Integer id, DrugCategoryEntity drugCategoryEntity) {
        Optional<DrugCategoryEntity> optionalDrugCategory = drugCategoryRepository.findById(id);
        if (optionalDrugCategory.isEmpty()) { throw new DrugCategoryNotFoundException(); }
        DrugCategoryEntity drugCategory = optionalDrugCategory.get();
        drugCategory.setId(id);
        return drugCategoryRepository.save(drugCategory);
    }
    public void deleteDrugCategory(Integer id) {
        Optional<DrugCategoryEntity> optionalDrugCategory = drugCategoryRepository.findById(id);
        if (optionalDrugCategory.isEmpty()) { throw new DrugCategoryNotFoundException(); }
        drugCategoryRepository.deleteById(id);
    }
}
