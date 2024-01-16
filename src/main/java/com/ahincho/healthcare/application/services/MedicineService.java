package com.ahincho.healthcare.application.services;

import com.ahincho.healthcare.domain.entities.CategoryEntity;
import com.ahincho.healthcare.domain.entities.MedicineEntity;
import com.ahincho.healthcare.domain.exceptions.categories.CategoryNotFoundException;
import com.ahincho.healthcare.domain.exceptions.medicines.MedicineDuplicatedException;
import com.ahincho.healthcare.domain.exceptions.medicines.MedicineNotFoundException;
import com.ahincho.healthcare.domain.repositories.CategoryRepository;
import com.ahincho.healthcare.domain.repositories.MedicineRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {
    private final MedicineRepository medicineRepository;
    private final CategoryRepository categoryRepository;
    public MedicineService(MedicineRepository medicineRepository, CategoryRepository categoryRepository) {
        this.medicineRepository = medicineRepository;
        this.categoryRepository = categoryRepository;
    }
    public List<MedicineEntity> getAllDrugs() {
        return medicineRepository.findAll();
    }
    public List<MedicineEntity> getDrugsByCategoryId(Integer categoryId) {
        return medicineRepository.findByCategoryId(categoryId);
    }
    @Transactional
    public MedicineEntity createDrug(MedicineEntity medicineEntity) throws MedicineDuplicatedException, CategoryNotFoundException {
        Optional<MedicineEntity> optionalDrug = medicineRepository.findByName(medicineEntity.getName());
        if (optionalDrug.isPresent()) { throw new MedicineDuplicatedException(); }
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(medicineEntity.getCategory().getId());
        if (optionalCategory.isEmpty()) { throw new CategoryNotFoundException(); }
        MedicineEntity savedMedicineEntity = medicineRepository.save(medicineEntity);
        savedMedicineEntity.setCategory(optionalCategory.get());
        return medicineRepository.save(savedMedicineEntity);
    }
    public MedicineEntity findDrugById(Integer id) throws MedicineNotFoundException {
        Optional<MedicineEntity> optionalDrug = medicineRepository.findById(id);
        if (optionalDrug.isEmpty()) { throw new MedicineNotFoundException(); }
        return optionalDrug.get();
    }
    @Transactional
    public void updateDrug(Integer id, MedicineEntity medicineEntity) throws MedicineNotFoundException, CategoryNotFoundException, MedicineDuplicatedException {
        Optional<MedicineEntity> optionalDrugId = medicineRepository.findById(id);
        if (optionalDrugId.isEmpty()) { throw new MedicineNotFoundException(); }
        Optional<MedicineEntity> optionalDrugName = medicineRepository.findByName(medicineEntity.getName());
        if (optionalDrugName.isPresent()) { throw new MedicineDuplicatedException(); }
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(medicineEntity.getCategory().getId());
        if (optionalCategory.isEmpty()) { throw new CategoryNotFoundException(); }
        medicineEntity.setId(id);
        medicineRepository.save(medicineEntity);
    }
    @Transactional
    public void deleteDrug(Integer id) throws MedicineNotFoundException {
        Optional<MedicineEntity> optionalDrug = medicineRepository.findById(id);
        if (optionalDrug.isEmpty()) { throw new MedicineNotFoundException(); }
        medicineRepository.deleteById(id);
    }
}
