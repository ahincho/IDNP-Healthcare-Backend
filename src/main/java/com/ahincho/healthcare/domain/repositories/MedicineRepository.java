package com.ahincho.healthcare.domain.repositories;

import com.ahincho.healthcare.domain.entities.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<MedicineEntity, Integer> {
    Optional<MedicineEntity> findByName(String name);
    List<MedicineEntity> findByCategoryId(Integer categoryId);
}
