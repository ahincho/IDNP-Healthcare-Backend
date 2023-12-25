package com.ahincho.healthcare.domain.entities;

import com.ahincho.healthcare.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "roles")
@Entity(name = "role")
@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Role role;
}
