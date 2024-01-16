package com.ahincho.healthcare.application.mappers;

import com.ahincho.healthcare.domain.dtos.users.UserRequest;
import com.ahincho.healthcare.domain.dtos.users.UserResponse;
import com.ahincho.healthcare.domain.entities.UserEntity;

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
}
