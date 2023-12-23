package com.ahincho.healthcare.domain.repositories;

import com.ahincho.healthcare.domain.entities.DrugCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DrugCategoryRepository extends JpaRepository<DrugCategoryEntity, Integer> {
    Optional<DrugCategoryEntity> findByName(String name);
}
