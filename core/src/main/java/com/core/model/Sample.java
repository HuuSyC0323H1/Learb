package com.core.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sample") // thưc thể mẫu
public class Sample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long imageId;
}
