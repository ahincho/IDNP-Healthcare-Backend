package com.ahincho.healthcare.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "drugs")
@Entity(name = "drug")
@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private DrugCategory drugCategory;
    private String description;
}
