package com.ahincho.healthcare.infrastructure.controllers;

import com.ahincho.healthcare.application.services.UserService;
import com.ahincho.healthcare.domain.dtos.UserRequest;
import com.ahincho.healthcare.domain.dtos.UserResponse;
import com.ahincho.healthcare.domain.entities.UserEntity;
import com.ahincho.healthcare.domain.exceptions.RoleNotFoundException;
import com.ahincho.healthcare.domain.exceptions.UserDuplicatedEmailException;
import com.ahincho.healthcare.domain.exceptions.UserDuplicatedUsernameException;
import com.ahincho.healthcare.domain.mappers.UserMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest userRequest) throws UserDuplicatedEmailException, UserDuplicatedUsernameException, RoleNotFoundException {
        UserEntity savedUserEntity = userService.createUser(UserMapper.requestToEntity(userRequest));
        return ResponseEntity.ok(UserMapper.entityToResponse(savedUserEntity));
    }
}
