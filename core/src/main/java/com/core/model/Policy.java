package com.core.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author: Huu Sy
 * @Date: 29/04/2024 7:39 CH
 */

@Data
@Entity
@Table(name = "policy")
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long productId;
}
