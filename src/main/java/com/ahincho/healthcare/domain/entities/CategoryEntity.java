package com.ahincho.healthcare.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "categories")
@Entity(name = "category")
@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
