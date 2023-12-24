package com.ahincho.healthcare.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please send a valid email")
    private String email;
    @NotBlank(message = "Password must not be blank")
    private String password;
}
