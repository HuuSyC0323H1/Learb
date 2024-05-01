package com.core.services;

import com.core.api.forms.FilterRequest;
import com.core.api.view.ProductDetailView;
import com.core.api.view.ProductFilterView;
import com.core.api.view.ProductView;
import com.core.api.view.ResponseProductDetail;
import com.core.model.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductView> getListProduct(FilterRequest filter, Pageable pageable);

    ProductFilterView getProductFilter(FilterRequest filter, Pageable pageable);

    ResponseProductDetail getDetailProduct(Long productId);
}
