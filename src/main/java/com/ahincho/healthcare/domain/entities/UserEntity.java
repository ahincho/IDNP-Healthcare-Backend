package com.ahincho.healthcare.domain.entities;

import com.ahincho.healthcare.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "users")
@Entity(name = "user")
@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastname;
    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
