package com.ahincho.healthcare.domain.repositories;

import com.ahincho.healthcare.domain.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

}
