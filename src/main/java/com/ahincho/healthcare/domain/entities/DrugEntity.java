package com.ahincho.healthcare.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "drugs")
@Entity(name = "drug")
@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrugEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity drugCategory;
    private String description;
}
