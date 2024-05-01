package com.core.api.view;

import com.core.model.*;
import lombok.Data;

@Data
public class ProductView {
    private Long id;

    private String name;

    private Double price;

    private Double rating;

    private String image;

    private Long quantitySold;

    private Level level;

    private ProvinceView province;
}
