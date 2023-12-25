package com.ahincho.healthcare.application.services;

import com.ahincho.healthcare.domain.entities.RoleEntity;
import com.ahincho.healthcare.domain.entities.UserEntity;
import com.ahincho.healthcare.domain.exceptions.RoleNotFoundException;
import com.ahincho.healthcare.domain.exceptions.UserDuplicatedEmailException;
import com.ahincho.healthcare.domain.exceptions.UserDuplicatedUsernameException;
import com.ahincho.healthcare.domain.exceptions.UserNotFoundException;
import com.ahincho.healthcare.domain.repositories.RoleRepository;
import com.ahincho.healthcare.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
    public UserEntity findUserById(Integer id) throws UserNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) { throw new UserNotFoundException(); }
        return optionalUser.get();
    }
    @Transactional
    public UserEntity createUser(UserEntity userEntity) throws UserDuplicatedEmailException, UserDuplicatedUsernameException, RoleNotFoundException {
        Optional<UserEntity> optionalUserEmail = userRepository.findByEmail(userEntity.getEmail());
        if (optionalUserEmail.isPresent()) { throw new UserDuplicatedEmailException(); }
        Optional<UserEntity> optionalUserUsername = userRepository.findByUsername(userEntity.getUsername());
        if (optionalUserUsername.isPresent()) { throw new UserDuplicatedUsernameException(); }
        Optional<RoleEntity> roleEntity = roleRepository.findByName("USER");
        if (roleEntity.isEmpty()) { throw new RoleNotFoundException(); }
        userEntity.setRoles(Set.of(roleEntity.get()));
        return userRepository.save(userEntity);
    }
    @Transactional
    public void updateUser(Integer id, UserEntity userEntity) throws UserNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) { throw new UserNotFoundException(); }
        userEntity.setId(id);
        userEntity.setRoles(optionalUser.get().getRoles());
        userRepository.save(userEntity);
    }
    @Transactional
    public void deleteUser(Integer id) throws UserNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) { throw new UserNotFoundException(); }
        userRepository.deleteById(id);
    }
}
