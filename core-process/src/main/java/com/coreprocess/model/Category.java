package com.coreprocess.model;

import lombok.Data;

import java.util.List;

/**
 * @author: Huu Sy
 * @Date: 01/05/2024 8:37 SA
 */

@Data
public class Category {

    private String name;
    private List<Subcategory> subcategories;
}
