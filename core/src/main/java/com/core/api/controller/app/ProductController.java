package com.core.api.controller.app;

import com.core.api.forms.FilterRequest;
import com.core.api.forms.ResponseObject;
import com.core.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/app/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/page")
    public ResponseObject getPageProduct(FilterRequest filter, Pageable pageable){
        return new ResponseObject(productService.getListProduct(filter, pageable));
    }

    @GetMapping("filter/page")
    public ResponseObject getPageFilter(FilterRequest filter, Pageable pageable){
        return new ResponseObject(productService.getProductFilter(filter, pageable));
    }

    @GetMapping("/detail")
    public ResponseObject getDetailProduct(Long id){
        return new ResponseObject(productService.getDetailProduct(id));
    }
}
