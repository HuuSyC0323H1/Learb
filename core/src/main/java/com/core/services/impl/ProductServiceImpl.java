package com.core.services.impl;

import com.core.api.forms.FilterRequest;
import com.core.api.view.ProductCategoryView;
import com.core.api.view.ProductFilterView;
import com.core.api.view.ProductView;
import com.core.infrastructure.constant.ErrorCode;
import com.core.infrastructure.constant.LanguageCode;
import com.core.infrastructure.exception.NVException;
import com.core.model.Category;
import com.core.model.Products;
import com.core.repositories.CategoryRepository;
import com.core.repositories.ProductRepository;
import com.core.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private final ModelMapper modelMapper = new ModelMapper();


    @Override
    public Page<Products> getListProduct(FilterRequest filter, Pageable pageable) {
        if (filter.getLangCode() == null){
            filter.setLangCode(LanguageCode.VI.getValue());
        }
        return productRepository.getListProduct(filter.getName(), filter.getLangCode(), pageable);
    }

    @Override
    public ProductFilterView getProductFilter(FilterRequest filter, Pageable pageable) {
        ProductFilterView productFilterView = new ProductFilterView();
        List<Products> productsList = productRepository.getProductsList(filter.getName());
        List<ProductView> productViewsList = new ArrayList<>();
        List<ProductCategoryView> productCategoryViewList = new ArrayList<>();
        productsList.forEach(products -> {
            ProductCategoryView productCategoryView = new ProductCategoryView();
            Category category = categoryRepository.getCategoryWithId(products.getCategoryId());
            productCategoryView.setName(category.getName());
            ProductView productView = modelMapper.map(products, ProductView.class);
            productCategoryViewList.add(productCategoryView);
            productViewsList.add(productView);
        });
        int startPage = (int) pageable.getOffset();
        int endPage = Math.min((startPage + pageable.getPageSize()), productViewsList.size());
        Page<ProductView> productViewPage = new PageImpl<>(productViewsList.subList(startPage, endPage), pageable, productViewsList.size());
        productFilterView.setCategoryViews(productCategoryViewList);
        productFilterView.setProductViews(productViewPage);
        return productFilterView;
    }

    @Override
    public Products getDetailProduct(Long productId) {
        Products products = productRepository.findAllById(productId);
        if (products == null){
            throw new NVException(ErrorCode.PRODUCT_DOES_NOT_EXIT);
        }
        return products;
    }
}
