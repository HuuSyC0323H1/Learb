package com.core.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private Long countryId;
    private Long sort;
    private Long langCodeId;
}
