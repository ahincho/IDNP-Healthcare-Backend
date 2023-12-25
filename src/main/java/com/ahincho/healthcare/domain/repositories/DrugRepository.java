package com.ahincho.healthcare.domain.repositories;

import com.ahincho.healthcare.domain.entities.DrugEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DrugRepository extends JpaRepository<DrugEntity, Integer> {
    Optional<DrugEntity> findByName(String name);
    List<DrugEntity> findByCategoryId(Integer integer);
}
