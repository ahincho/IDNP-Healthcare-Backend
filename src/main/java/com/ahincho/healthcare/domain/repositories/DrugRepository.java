package com.ahincho.healthcare.domain.repositories;

import com.ahincho.healthcare.domain.entities.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Integer> {
    
}
