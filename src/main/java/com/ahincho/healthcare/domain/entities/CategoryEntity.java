package com.ahincho.healthcare.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "drug_categories")
@Entity(name = "drug_category")
@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
