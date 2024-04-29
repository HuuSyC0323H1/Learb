package com.core.api.forms;

import lombok.Data;

@Data
public class FilterRequest {
    private String name;
    private String langCode;
    private Long categoryId;
    private Long provinceId;
    private Long partnerId;
    private Long trademarkId;
    private Double minPrice;
    private Double maxPrice;
    private Long styleShop;
    private Long conditionId;
    private Long rating;
    private Long policyId;
}
