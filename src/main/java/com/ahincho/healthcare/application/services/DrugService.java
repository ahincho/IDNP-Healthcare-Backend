package com.ahincho.healthcare.application.services;

import com.ahincho.healthcare.domain.entities.CategoryEntity;
import com.ahincho.healthcare.domain.entities.DrugEntity;
import com.ahincho.healthcare.domain.exceptions.CategoryNotFoundException;
import com.ahincho.healthcare.domain.exceptions.DrugDuplicatedException;
import com.ahincho.healthcare.domain.exceptions.DrugNotFoundException;
import com.ahincho.healthcare.domain.repositories.CategoryRepository;
import com.ahincho.healthcare.domain.repositories.DrugRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DrugService {
    private final DrugRepository drugRepository;
    private final CategoryRepository categoryRepository;
    public DrugService(DrugRepository drugRepository, CategoryRepository categoryRepository) {
        this.drugRepository = drugRepository;
        this.categoryRepository = categoryRepository;
    }
    public List<DrugEntity> getAllDrugs() {
        return drugRepository.findAll();
    }
    public List<DrugEntity> getDrugsByCategoryId(Integer categoryId) {
        return drugRepository.findByCategoryId(categoryId);
    }
    @Transactional
    public DrugEntity createDrug(DrugEntity drugEntity) throws DrugDuplicatedException, CategoryNotFoundException {
        Optional<DrugEntity> optionalDrug = drugRepository.findByName(drugEntity.getName());
        if (optionalDrug.isPresent()) { throw new DrugDuplicatedException(); }
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(drugEntity.getCategory().getId());
        if (optionalCategory.isEmpty()) { throw new CategoryNotFoundException(); }
        DrugEntity savedDrugEntity = drugRepository.save(drugEntity);
        savedDrugEntity.setCategory(optionalCategory.get());
        return drugRepository.save(savedDrugEntity);
    }
    public DrugEntity findDrugById(Integer id) throws DrugNotFoundException {
        Optional<DrugEntity> optionalDrug = drugRepository.findById(id);
        if (optionalDrug.isEmpty()) { throw new DrugNotFoundException(); }
        return optionalDrug.get();
    }
    @Transactional
    public void updateDrug(Integer id, DrugEntity drugEntity) throws DrugNotFoundException, CategoryNotFoundException, DrugDuplicatedException {
        Optional<DrugEntity> optionalDrugId = drugRepository.findById(id);
        if (optionalDrugId.isEmpty()) { throw new DrugNotFoundException(); }
        Optional<DrugEntity> optionalDrugName = drugRepository.findByName(drugEntity.getName());
        if (optionalDrugName.isPresent()) { throw new DrugDuplicatedException(); }
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(drugEntity.getCategory().getId());
        if (optionalCategory.isEmpty()) { throw new CategoryNotFoundException(); }
        drugEntity.setId(id);
        drugRepository.save(drugEntity);
    }
    @Transactional
    public void deleteDrug(Integer id) throws DrugNotFoundException {
        Optional<DrugEntity> optionalDrug = drugRepository.findById(id);
        if (optionalDrug.isEmpty()) { throw new DrugNotFoundException(); }
        drugRepository.deleteById(id);
    }
}
