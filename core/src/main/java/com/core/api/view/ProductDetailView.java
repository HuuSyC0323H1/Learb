package com.core.api.view;

import com.core.model.Brands;
import com.core.model.Condition;
import com.core.model.Level;
import com.core.model.StyleShop;
import lombok.Data;

@Data
public class ProductDetailView {
    private Long id;

    private String name;

    private String description;

    private String status;

    private Long quantity;

    private Double startNumber;

    private Double price;

    private Double rating;

    private String image;

    private Long quantitySold;

    private Long quantityTotal;

    private String material;

    private String warrantyPeriod;

    private Level level;

    private Condition condition;

    private ProvinceView province;

    private StyleShop styleShop;

    private Brands brands;
}
