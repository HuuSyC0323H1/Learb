package com.core.services;

import com.core.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getListCategory(String name);
}
