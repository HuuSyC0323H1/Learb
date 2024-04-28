package com.core.api.controller.app;

import com.core.api.forms.ResponseObject;
import com.core.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/app/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseObject getListCategory(@RequestParam(value = "name") String name){
        return new ResponseObject(categoryService.getListCategory(name));
    }
}
