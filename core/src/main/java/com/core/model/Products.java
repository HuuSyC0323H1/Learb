package com.core.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String status;

    private Double price;

    private Long quantity;

    private Double startNumber;

    private Double rating;

    private Long quantitySold;

    private Long quantityTotal;

    private String  policy;

    private Long levelId;

    private Long userId;

    private Long categoryId;

    private String material;

    private String warrantyPeriod;

    private String style;

    private Long provinceId;

}
