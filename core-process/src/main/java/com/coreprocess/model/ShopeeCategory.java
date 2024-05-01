package com.coreprocess.model;

/**
 * @author: Huu Sy
 * @Date: 01/05/2024 8:21 SA
 */

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection  = "shopee")
public class ShopeeCategory {

    @Id
    private String id;
    private String name;

    private List<Category> categories;

}