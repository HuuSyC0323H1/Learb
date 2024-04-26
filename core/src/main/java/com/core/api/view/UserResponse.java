package com.core.api.view;

import lombok.Data;

/**
 * @author: Huu Sy
 * @Date: 27/04/2024 5:52 SA
 */

@Data
public class UserResponse {
    private Long id;

    private String fullName;

    private String userName;

    private String email;

    private String role;
}
