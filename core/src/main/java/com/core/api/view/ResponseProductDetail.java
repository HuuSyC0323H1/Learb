package com.core.api.view;

import lombok.Data;

/**
 * @author: Huu Sy
 * @Date: 01/05/2024 8:09 SA
 */

@Data
public class ResponseProductDetail {

    private CategoryProduct category;
    private ProductDetailView productDetail;
}
