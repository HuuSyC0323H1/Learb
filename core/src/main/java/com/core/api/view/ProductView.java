package com.core.api.view;

import lombok.Data;

@Data
public class ProductView {
    private Long id;

    private String name;

    private Double price;

    private Long quantitySold;

    private Long levelId;

    private Long provinceId;
}
