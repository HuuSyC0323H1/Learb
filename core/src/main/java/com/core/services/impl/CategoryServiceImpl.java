package com.core.services.impl;

import com.core.model.Category;
import com.core.repositories.CategoryRepository;
import com.core.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getListCategory(String name) {
        return categoryRepository.getListCategory(name);
    }
}
