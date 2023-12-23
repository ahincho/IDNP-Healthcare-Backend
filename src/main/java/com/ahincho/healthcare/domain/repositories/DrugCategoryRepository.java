package com.ahincho.healthcare.domain.repositories;

import com.ahincho.healthcare.domain.entities.DrugCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugCategoryRepository extends JpaRepository<DrugCategoryEntity, Integer> {
    
}
