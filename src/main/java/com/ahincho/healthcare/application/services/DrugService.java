package com.ahincho.healthcare.application.services;

import com.ahincho.healthcare.domain.entities.DrugEntity;
import com.ahincho.healthcare.domain.exceptions.DrugDuplicatedException;
import com.ahincho.healthcare.domain.exceptions.DrugNotFoundException;
import com.ahincho.healthcare.domain.repositories.DrugRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DrugService {
    private final DrugRepository drugRepository;
    public DrugService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }
    public List<DrugEntity> getAllDrugs() {
        return drugRepository.findAll();
    }
    public List<DrugEntity> getDrugsByCategoryId(Integer categoryId) {
        return drugRepository.getDrugEntityByCategoryId(categoryId);
    }
    public DrugEntity createDrug(DrugEntity drugEntity) {
        Optional<DrugEntity> optionalDrug = drugRepository.getDrugEntityByName(drugEntity.getName());
        if (optionalDrug.isPresent()) { throw new DrugDuplicatedException(); }
        return drugRepository.save(drugEntity);
    }
    public DrugEntity findDrugById(Integer id) {
        Optional<DrugEntity> optionalDrug = drugRepository.findById(id);
        if (optionalDrug.isEmpty()) { throw new DrugNotFoundException(); }
        return optionalDrug.get();
    }
    public DrugEntity updateDrug(Integer id, DrugEntity drugEntity) {
        Optional<DrugEntity> optionalDrug = drugRepository.findById(id);
        if (optionalDrug.isEmpty()) { throw new DrugNotFoundException(); }
        drugEntity.setId(id);
        return drugRepository.save(drugEntity);
    }
    public void deleteDrug(Integer id) {
        Optional<DrugEntity> optionalDrug = drugRepository.findById(id);
        if (optionalDrug.isEmpty()) { throw new DrugNotFoundException(); }
        drugRepository.deleteById(id);
    }
}
