package com.core.services.impl;

import com.core.api.forms.FilterRequest;
import com.core.api.view.*;
import com.core.infrastructure.constant.ErrorCode;
import com.core.infrastructure.constant.LanguageCode;
import com.core.infrastructure.exception.NVException;
import com.core.model.*;
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
    private ImageRepository imageRepository;

    @Autowired
    private StyleShopRepository styleShopRepository;

    @Autowired
    private TrademarkRepository trademarkRepository;

    @Autowired
    private ConditionRepository conditionRepository;

    private final ModelMapper modelMapper = new ModelMapper();


    @Override
    public Page<ProductView> getListProduct(FilterRequest filter, Pageable pageable) {
        if (filter.getLangCode() == null){
            filter.setLangCode(LanguageCode.VI.getValue());
        }
        Page<Products> productsList = productRepository.getListProduct(filter.getName(), pageable);
        List<ProductView> productViews = new ArrayList<>();
        productsList.getContent().forEach(products -> {
            Level level = levelRepository.findLevelById(products.getLevelId());
            ProvinceView province = provinceRepository.findProvinceById(products.getProvinceId());
            Image image = imageRepository.findRandomImageByProductId(products.getId());

            ProductView productView = modelMapper.map(products, ProductView.class);
            productView.setLevel(level);
            productView.setProvince(province);
            productView.setImage(image.getLink());
            productViews.add(productView);
        });
        return new PageImpl<>(productViews, pageable, productsList.getTotalElements());
    }

    @Override
    public ProductFilterView getProductFilter(FilterRequest filter, Pageable pageable) {
        ProductFilterView productFilterView = new ProductFilterView();
        List<Products> productsList = productRepository.getProductsList(filter.getName());

        if (productsList.isEmpty()) throw new NVException(ErrorCode.IS_EMPTY);

        if (filter.getCategoryId() != null && !filter.getCategoryId().isEmpty()){
            productsList = productsList.stream().filter(productView ->
                    filter.getCategoryId().contains(productView.getCategoryId())).collect(Collectors.toList());
        }

        if (filter.getProvinceId() != null && !filter.getProvinceId().isEmpty()){
            productsList = productsList.stream().filter(productView ->
                    filter.getProvinceId().contains(productView.getProvinceId())).collect(Collectors.toList());
        }

        if (filter.getRating() != null){
            productsList = productsList.stream().filter(productView ->
                    productView.getRating().equals(filter.getRating())).collect(Collectors.toList());
        }

        if (filter.getMinPrice() != null && filter.getMaxPrice() != null){
            productsList = productsList.stream() .filter(productView ->
                            productView.getPrice() >= filter.getMinPrice() && productView.getPrice() <= filter.getMaxPrice())
                    .collect(Collectors.toList());
        }

        if (filter.getPolicyId() != null && !filter.getPolicyId().isEmpty()){
            productsList = productsList.stream().filter(productView ->
                    filter.getPolicyId().contains(productView.getPolicyId())).collect(Collectors.toList());
        }

        if (filter.getTrademarkId() != null && !filter.getTrademarkId().isEmpty()){
            productsList = productsList.stream().filter(productView ->
                    filter.getTrademarkId().contains(productView.getTrademarkId())).collect(Collectors.toList());
        }

        if (filter.getStyleShop() != null && !filter.getStyleShop().isEmpty()){
            productsList = productsList.stream().filter(productView ->
                    filter.getStyleShop().contains(productView.getStyleId())).collect(Collectors.toList());
        }

        if (filter.getConditionId() != null && !filter.getConditionId().isEmpty()){
            productsList = productsList.stream().filter(productView ->
                    filter.getConditionId().contains(productView.getConditionId())).collect(Collectors.toList());
        }

        List<ProductView> productViewsList = new ArrayList<>();
        Set<ProductCategoryView> productCategoryViewList = new HashSet<>();

        productsList.forEach(products -> {
            ProductCategoryView productCategoryView = new ProductCategoryView();
            Category category = categoryRepository.getCategoryById(products.getCategoryId());
            productCategoryView.setName(category.getName());

            Level level = levelRepository.findLevelById(products.getLevelId());
            ProvinceView province = provinceRepository.findProvinceById(products.getProvinceId());
            Image image = imageRepository.findRandomImageByProductId(products.getId());

            ProductView productView = modelMapper.map(products, ProductView.class);
            productView.setLevel(level);
            productView.setProvince(province);
            productView.setImage(image.getLink());
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
    public ResponseProductDetail getDetailProduct(Long productId) {
        ResponseProductDetail productDetail = new ResponseProductDetail();
        Products products = productRepository.findAllById(productId);

        ProductDetailView productDetailView = modelMapper.map(products, ProductDetailView.class);

        Level level = levelRepository.findLevelById(products.getLevelId());
        ProvinceView province = provinceRepository.findProvinceById(products.getProvinceId());
        Image image = imageRepository.findRandomImageByProductId(products.getId());
        StyleShop styleShop = styleShopRepository.findStyleShopById(products.getStyleId());
        Brands brands = trademarkRepository.findBrandsById(products.getTrademarkId());
        Condition condition = conditionRepository.findConditionById(products.getConditionId());

        productDetailView.setLevel(level);
        productDetailView.setProvince(province);
        productDetailView.setImage(image.getLink());
        productDetailView.setBrands(brands);
        productDetailView.setStyleShop(styleShop);
        productDetailView.setCondition(condition);

        productDetail.setProductDetail(productDetailView);
        return productDetail;
    }
}
