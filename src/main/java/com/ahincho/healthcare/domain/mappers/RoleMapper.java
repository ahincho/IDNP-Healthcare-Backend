package com.ahincho.healthcare.domain.mappers;

import com.ahincho.healthcare.domain.dtos.RoleRequest;
import com.ahincho.healthcare.domain.dtos.RoleResponse;
import com.ahincho.healthcare.domain.entities.RoleEntity;

public class RoleMapper {
    public static RoleResponse entityToResponse(RoleEntity roleEntity) {
        return RoleResponse.builder()
                .id(roleEntity.getId())
                .name(roleEntity.getName())
                .build();
    }
    public static RoleEntity requestToEntity(RoleRequest roleRequest) {
        return RoleEntity.builder()
                .name(roleRequest.getName())
                .build();
    }
}
