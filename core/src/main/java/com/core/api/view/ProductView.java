package com.core.api.view;

import com.core.model.Level;
import lombok.Data;

@Data
public class ProductView {
    private Long id;

    private String name;

    private Double price;

    private Long quantitySold;

    private Level level;

    private ProvinceView provinceView;
}
