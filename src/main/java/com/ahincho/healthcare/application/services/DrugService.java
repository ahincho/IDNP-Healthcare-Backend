package com.ahincho.healthcare.application.services;

import com.ahincho.healthcare.domain.entities.CategoryEntity;
import com.ahincho.healthcare.domain.entities.MedicineEntity;
import com.ahincho.healthcare.domain.exceptions.categories.CategoryNotFoundException;
import com.ahincho.healthcare.domain.exceptions.medicines.MedicineDuplicatedException;
import com.ahincho.healthcare.domain.exceptions.medicines.MedicineNotFoundException;
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
    public List<MedicineEntity> getAllDrugs() {
        return drugRepository.findAll();
    }
    public List<MedicineEntity> getDrugsByCategoryId(Integer categoryId) {
        return drugRepository.findByCategoryId(categoryId);
    }
    @Transactional
    public MedicineEntity createDrug(MedicineEntity medicineEntity) throws MedicineDuplicatedException, CategoryNotFoundException {
        Optional<MedicineEntity> optionalDrug = drugRepository.findByName(medicineEntity.getName());
        if (optionalDrug.isPresent()) { throw new MedicineDuplicatedException(); }
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(medicineEntity.getCategory().getId());
        if (optionalCategory.isEmpty()) { throw new CategoryNotFoundException(); }
        MedicineEntity savedMedicineEntity = drugRepository.save(medicineEntity);
        savedMedicineEntity.setCategory(optionalCategory.get());
        return drugRepository.save(savedMedicineEntity);
    }
    public MedicineEntity findDrugById(Integer id) throws MedicineNotFoundException {
        Optional<MedicineEntity> optionalDrug = drugRepository.findById(id);
        if (optionalDrug.isEmpty()) { throw new MedicineNotFoundException(); }
        return optionalDrug.get();
    }
    @Transactional
    public void updateDrug(Integer id, MedicineEntity medicineEntity) throws MedicineNotFoundException, CategoryNotFoundException, MedicineDuplicatedException {
        Optional<MedicineEntity> optionalDrugId = drugRepository.findById(id);
        if (optionalDrugId.isEmpty()) { throw new MedicineNotFoundException(); }
        Optional<MedicineEntity> optionalDrugName = drugRepository.findByName(medicineEntity.getName());
        if (optionalDrugName.isPresent()) { throw new MedicineDuplicatedException(); }
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(medicineEntity.getCategory().getId());
        if (optionalCategory.isEmpty()) { throw new CategoryNotFoundException(); }
        medicineEntity.setId(id);
        drugRepository.save(medicineEntity);
    }
    @Transactional
    public void deleteDrug(Integer id) throws MedicineNotFoundException {
        Optional<MedicineEntity> optionalDrug = drugRepository.findById(id);
        if (optionalDrug.isEmpty()) { throw new MedicineNotFoundException(); }
        drugRepository.deleteById(id);
    }
}
