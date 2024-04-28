package com.core.api.view;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class ProductFilterView {

    private List<ProductCategoryView> categoryViews;
    private Page<ProductView> productViews;
}
