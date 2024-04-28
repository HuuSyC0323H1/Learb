package com.core.services.impl;

import com.core.infrastructure.constant.LanguageCode;
import com.core.model.Products;
import com.core.repositories.ProductRepository;
import com.core.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Products> getListProduct(String name, String langCode, Pageable pageable) {
        if (langCode == null){
            langCode = LanguageCode.VI.getValue();
        }
        return productRepository.getListProduct(name, langCode, pageable);
    }
}
