package com.ahincho.healthcare.domain.mappers;

import com.ahincho.healthcare.domain.dtos.UserRequest;
import com.ahincho.healthcare.domain.dtos.UserResponse;
import com.ahincho.healthcare.domain.dtos.UserUpdateRequest;
import com.ahincho.healthcare.domain.entities.RoleEntity;
import com.ahincho.healthcare.domain.entities.UserEntity;

import java.util.stream.Collectors;

public class UserMapper {
    public static UserResponse entityToResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .lastname(userEntity.getLastname())
                .username(userEntity.getUsername())
                .build();
    }
    public static UserEntity requestToEntity(UserRequest userRequest) {
        return UserEntity.builder()
                .name(userRequest.getName())
                .lastname(userRequest.getLastname())
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }
    public static UserEntity updateRequestToEntity(UserUpdateRequest userUpdateRequest) {
        return UserEntity.builder()
                .name(userUpdateRequest.getName())
                .lastname(userUpdateRequest.getLastname())
                .username(userUpdateRequest.getUsername())
                .build();
    }
}
