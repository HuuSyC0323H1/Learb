package com.core.services;

import com.core.model.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<Products> getListProduct(String name, String langCode, Pageable pageable);
}
