package com.core.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author: Huu Sy
 * @Date: 24/04/2024 11:04 CH
 */

@Data
@Entity
@Table(name = "languages")
public class Languages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
}
