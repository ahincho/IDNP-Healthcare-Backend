package com.ahincho.healthcare.domain.repositories;

import com.ahincho.healthcare.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findUserEntityByUsername(String username);
    Optional<UserEntity> findUserEntityByEmail(String email);
    Optional<UserEntity> findUserEntityByEmailAndPassword(String email, String password);
}
