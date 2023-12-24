package com.ahincho.healthcare.application.services;

import com.ahincho.healthcare.domain.entities.UserEntity;
import com.ahincho.healthcare.domain.enums.Role;
import com.ahincho.healthcare.domain.exceptions.UserDuplicatedException;
import com.ahincho.healthcare.domain.exceptions.UserNotFoundException;
import com.ahincho.healthcare.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserEntity findUserByEmailAndPassword(String email, String password) throws UserNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findUserEntityByEmailAndPassword(email, password);
        if (optionalUser.isEmpty()) { throw new UserNotFoundException(); }
        return optionalUser.get();
    }
    public UserEntity createUser(UserEntity userEntity) throws UserDuplicatedException {
        Optional<UserEntity> optionalUser = userRepository.findUserEntityByEmail(userEntity.getEmail());
        if (optionalUser.isPresent()) { throw new UserDuplicatedException(); }
        userEntity.setRole(Role.VIEWER);
        return userRepository.save(userEntity);
    }
    public void updateUser(Integer id, UserEntity userEntity) throws UserNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) { throw new UserNotFoundException(); }
        userEntity.setId(id);
        userRepository.save(userEntity);
    }
}
