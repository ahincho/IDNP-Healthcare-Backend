package com.ahincho.healthcare.infrastructure.controllers;

import com.ahincho.healthcare.application.services.UserService;
import com.ahincho.healthcare.domain.dtos.LoginRequest;
import com.ahincho.healthcare.domain.dtos.UserRequest;
import com.ahincho.healthcare.domain.dtos.UserResponse;
import com.ahincho.healthcare.domain.entities.UserEntity;
import com.ahincho.healthcare.domain.enums.Role;
import com.ahincho.healthcare.domain.exceptions.UserDuplicatedException;
import com.ahincho.healthcare.domain.exceptions.UserNotFoundException;
import com.ahincho.healthcare.domain.mappers.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserRequest userRequest, UriComponentsBuilder uriComponentsBuilder) throws UserDuplicatedException {
        UserEntity userEntity = userService.createUser(UserMapper.requestToEntity(userRequest));
        userEntity.setRole(Role.VIEWER);
        URI uri = uriComponentsBuilder.path("/login").build().toUri();
        return ResponseEntity.created(uri).body(UserMapper.entityToResponse(userEntity));
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody @Valid LoginRequest loginRequest) throws UserNotFoundException {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        UserEntity userEntity = userService.findUserByEmailAndPassword(email, password);
        return ResponseEntity.ok(UserMapper.entityToResponse(userEntity));
    }
}
