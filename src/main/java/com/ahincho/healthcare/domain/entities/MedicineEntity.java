package com.ahincho.healthcare.domain.entities;

import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.*;

@Table(name = "medicines")
@Entity(name = "medicine")
@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;
    private String description;
    private String imageUrl;
}
