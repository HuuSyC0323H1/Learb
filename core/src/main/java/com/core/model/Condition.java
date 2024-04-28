package com.core.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "condition")
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long langCodeId;
}
