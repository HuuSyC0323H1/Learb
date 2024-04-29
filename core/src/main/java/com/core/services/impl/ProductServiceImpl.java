package com.core.services.impl;

import com.core.api.forms.FilterRequest;
import com.core.api.view.ProductCategoryView;
import com.core.api.view.ProductFilterView;
import com.core.api.view.ProductView;
import com.core.api.view.ProvinceView;
import com.core.infrastructure.constant.ErrorCode;
import com.core.infrastructure.constant.LanguageCode;
import com.core.infrastructure.exception.NVException;
import com.core.model.Category;
import com.core.model.Level;
import com.core.model.Policy;
import com.core.model.Products;
import com.core.repositories.*;
import com.core.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private PolicyRepository policyRepository;

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

        if (productsList.isEmpty()) throw new NVException(ErrorCode.IS_EMPTY);
        if (filter.getCategoryId() != null){
            productsList = productsList.stream().filter(productView ->
                    productView.getCategoryId().compareTo(filter.getCategoryId()) == 0).toList();
        }

        if (filter.getProvinceId() != null){
            productsList = productsList.stream().filter(productView ->
                    productView.getProvinceId().compareTo(filter.getProvinceId()) == 0).toList();
        }

        if (filter.getMinPrice() != null && filter.getMaxPrice() != null){
            productsList = productsList.stream() .filter(productView ->
                            productView.getPrice() >= filter.getMinPrice() && productView.getPrice() <= filter.getMaxPrice())
                    .collect(Collectors.toList());
        }

//        if (filter.getPolicyId() != null){
//            productsList = productsList.stream().forEach(productView ->{
//                Policy policy = policyRepository.findPoliciesById(filter.getPolicyId());
//
//            });
//        }
        List<ProductView> productViewsList = new ArrayList<>();
        Set<ProductCategoryView> productCategoryViewList = new HashSet<>();

        productsList.forEach(products -> {
            ProductCategoryView productCategoryView = new ProductCategoryView();
            Category category = categoryRepository.getCategoryById(products.getCategoryId());
            productCategoryView.setName(category.getName());
            Level level = levelRepository.findLevelById(products.getLevelId());
            ProvinceView province = provinceRepository.findProvinceById(products.getProvinceId());
            ProductView productView = modelMapper.map(products, ProductView.class);
            productView.setLevel(level);
            productView.setProvinceView(province);
            productCategoryViewList.add(productCategoryView);
            productViewsList.add(productView);
        });


        int startPage = (int) pageable.getOffset();
        int endPage = Math.min((startPage + pageable.getPageSize()), productViewsList.size());
        Page<ProductView> productViewPage = new PageImpl<>(productViewsList.subList(startPage, endPage), pageable, productViewsList.size());

        List<ProductCategoryView> categoryViewsList = new ArrayList<>(productCategoryViewList);
        productFilterView.setCategoryViews(categoryViewsList);

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
