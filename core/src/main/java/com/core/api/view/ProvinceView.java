package com.core.api.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Huu Sy
 * @Date: 29/04/2024 7:10 CH
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceView {

    private Long id;
    private String code;
    private String name;
}
