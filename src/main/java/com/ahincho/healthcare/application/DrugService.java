package com.ahincho.healthcare.application;

import com.ahincho.healthcare.domain.entities.Drug;
import com.ahincho.healthcare.domain.repositories.DrugCategoryRepository;
import com.ahincho.healthcare.domain.repositories.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DrugService {
    @Autowired
    private DrugRepository drugRepository;
    public List<Drug> getAllDrugs() {
        return drugRepository.findAll();
    }
    public List<Drug> getDrugsByCategoryId(Integer drugCategoryId) {
        return drugRepository.getDrugsByDrugCategoryId(drugCategoryId);
    }
    public Drug createDrug(Drug drug) {
        return drugRepository.save(drug);
    }
    public Optional<Drug> findDrugById(Integer id) {
        return drugRepository.findById(id);
    }
    public Drug updateDrug(Drug drug) {
        return drugRepository.save(drug);
    }
    public void deleteDrug(Integer id) {
        drugRepository.deleteById(id);
    }
}
