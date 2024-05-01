package com.core.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author: Huu Sy
 * @Date: 30/04/2024 4:18 CH
 */

@Data
@Entity
@Table(name = "brands")
public class Brands {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
