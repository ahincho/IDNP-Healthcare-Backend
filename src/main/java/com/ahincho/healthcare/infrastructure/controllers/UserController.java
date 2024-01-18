package com.ahincho.healthcare.infrastructure.controllers;

import com.ahincho.healthcare.application.services.UserService;
import com.ahincho.healthcare.domain.dtos.users.UserRequest;
import com.ahincho.healthcare.domain.dtos.users.UserResponse;
import com.ahincho.healthcare.domain.entities.UserEntity;
import com.ahincho.healthcare.domain.exceptions.roles.RoleNotFoundException;
import com.ahincho.healthcare.domain.exceptions.users.UserDuplicatedEmailException;
import com.ahincho.healthcare.domain.exceptions.users.UserDuplicatedUsernameException;
import com.ahincho.healthcare.domain.exceptions.users.UserNotFoundException;
import com.ahincho.healthcare.application.mappers.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAll() {
        List<UserEntity> userEntities = userService.getAllUsers();
        if (userEntities.isEmpty()) { return ResponseEntity.noContent().build(); }
        return ResponseEntity.ok(userEntities.stream().map(UserMapper::entityToResponse).toList());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> findById(@PathVariable("id") Integer id) throws UserNotFoundException {
        UserEntity userEntity = userService.findUserById(id);
        return ResponseEntity.ok(UserMapper.entityToResponse(userEntity));
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserRequest userRequest, UriComponentsBuilder uriComponentsBuilder) throws UserDuplicatedEmailException, UserDuplicatedUsernameException, RoleNotFoundException {
        UserEntity savedUserEntity = userService.createUser(UserMapper.requestToEntity(userRequest));
        URI uri = uriComponentsBuilder.path("/api/users/{id}").buildAndExpand(savedUserEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(UserMapper.entityToResponse(savedUserEntity));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody @Valid UserRequest userUpdateRequest) throws UserNotFoundException, UserDuplicatedUsernameException {
        userService.updateUser(id, UserMapper.requestToEntity(userUpdateRequest));
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws UserNotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
