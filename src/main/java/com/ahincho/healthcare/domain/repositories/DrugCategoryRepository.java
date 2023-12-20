package com.ahincho.healthcare.domain.repositories;

import com.ahincho.healthcare.domain.entities.DrugCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugCategoryRepository extends CrudRepository<DrugCategory, Integer> {
    
}
