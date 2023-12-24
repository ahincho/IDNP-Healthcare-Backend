package com.ahincho.healthcare.domain.dtos;

import com.ahincho.healthcare.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Integer id;
    private String name;
    private String lastname;
    private String username;
    private Role role;
}
