package com.core.services;

import com.core.api.forms.FilterRequest;
import com.core.api.view.ProductFilterView;
import com.core.model.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<Products> getListProduct(FilterRequest filter, Pageable pageable);

    ProductFilterView getProductFilter(FilterRequest filter, Pageable pageable);

    Products getDetailProduct(Long productId);
}
