package com.ahincho.healthcare.infrastructure.controllers;

import com.ahincho.healthcare.application.services.UserService;
import com.ahincho.healthcare.domain.dtos.LoginRequest;
import com.ahincho.healthcare.domain.dtos.UserRequest;
import com.ahincho.healthcare.domain.dtos.UserResponse;
import com.ahincho.healthcare.domain.entities.UserEntity;
import com.ahincho.healthcare.domain.exceptions.UserDuplicatedException;
import com.ahincho.healthcare.domain.exceptions.UserNotFoundException;
import com.ahincho.healthcare.domain.mappers.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserRequest userRequest, UriComponentsBuilder uriComponentsBuilder) throws UserDuplicatedException {
        UserEntity userEntity = UserMapper.requestToEntity(userRequest);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        UserEntity savedUserEntity = userService.createUser(userEntity);
        URI uri = uriComponentsBuilder.path("/api/users/login").build().toUri();
        return ResponseEntity.created(uri).body(UserMapper.entityToResponse(savedUserEntity));
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody @Valid LoginRequest loginRequest) throws UserNotFoundException {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        UserEntity userEntity = userService.findUserByEmailAndPassword(email, password);
        return ResponseEntity.ok(UserMapper.entityToResponse(userEntity));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody @Valid UserRequest userRequest) throws UserNotFoundException {
        userService.updateUser(id, UserMapper.requestToEntity(userRequest));
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/upgrade/{id}")
    private ResponseEntity<Void> upgrade(@PathVariable("id") Integer id) throws UserNotFoundException {
        userService.makeAdmin(id);
        return ResponseEntity.notFound().build();
    }
}
