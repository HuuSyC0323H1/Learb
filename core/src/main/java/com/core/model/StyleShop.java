package com.core.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author: Huu Sy
 * @Date: 30/04/2024 4:11 CH
 */

@Data
@Entity
@Table(name = "style_shop")
public class StyleShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
