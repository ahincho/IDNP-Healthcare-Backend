package com.ahincho.healthcare.domain.mappers;

import com.ahincho.healthcare.domain.dtos.UserRequest;
import com.ahincho.healthcare.domain.dtos.UserResponse;
import com.ahincho.healthcare.domain.entities.UserEntity;

public class UserMapper {
    public static UserResponse entityToResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .lastname(userEntity.getLastname())
                .username(userEntity.getUsername())
                .role(userEntity.getRole())
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
}
