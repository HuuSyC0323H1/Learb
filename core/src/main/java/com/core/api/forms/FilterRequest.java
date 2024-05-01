package com.core.api.forms;

import lombok.Data;

import java.util.List;

@Data
public class FilterRequest {
    private String name;
    private String langCode;
    private List<Long> categoryId;
    private List<Long> provinceId;
    private List<Long> policyId;
    private List<Long> trademarkId;
    private Double minPrice;
    private Double maxPrice;
    private List<Long> styleShop;
    private List<Long> conditionId;
    private Double rating;

}
