package com.core.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "level")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
