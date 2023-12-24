package com.ahincho.healthcare.application.services;

import com.ahincho.healthcare.domain.entities.CategoryEntity;
import com.ahincho.healthcare.domain.entities.DrugEntity;
import com.ahincho.healthcare.domain.exceptions.CategoryNotFoundException;
import com.ahincho.healthcare.domain.exceptions.DrugDuplicatedException;
import com.ahincho.healthcare.domain.exceptions.DrugNotFoundException;
import com.ahincho.healthcare.domain.repositories.CategoryRepository;
import com.ahincho.healthcare.domain.repositories.DrugRepository;
import org.springframework.stereotype.Service;
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
        return drugRepository.getDrugEntityByCategoryId(categoryId);
    }
    public DrugEntity createDrug(DrugEntity drugEntity) throws DrugDuplicatedException, CategoryNotFoundException {
        Optional<DrugEntity> optionalDrug = drugRepository.getDrugEntityByName(drugEntity.getName());
        if (optionalDrug.isPresent()) { throw new DrugDuplicatedException(); }
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(drugEntity.getCategory().getId());
        if (optionalCategory.isEmpty()) { throw new CategoryNotFoundException(); }
        return drugRepository.save(drugEntity);
    }
    public DrugEntity findDrugById(Integer id) throws DrugNotFoundException {
        Optional<DrugEntity> optionalDrug = drugRepository.findById(id);
        if (optionalDrug.isEmpty()) { throw new DrugNotFoundException(); }
        return optionalDrug.get();
    }
    public void updateDrug(Integer id, DrugEntity drugEntity) throws DrugNotFoundException, CategoryNotFoundException {
        Optional<DrugEntity> optionalDrug = drugRepository.findById(id);
        if (optionalDrug.isEmpty()) { throw new DrugNotFoundException(); }
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(drugEntity.getCategory().getId());
        if (optionalCategory.isEmpty()) { throw new CategoryNotFoundException(); }
        drugEntity.setId(id);
        drugRepository.save(drugEntity);
    }
    public void deleteDrug(Integer id) throws DrugNotFoundException {
        Optional<DrugEntity> optionalDrug = drugRepository.findById(id);
        if (optionalDrug.isEmpty()) { throw new DrugNotFoundException(); }
        drugRepository.deleteById(id);
    }
}
