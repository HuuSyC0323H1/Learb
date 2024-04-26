package com.core.api.view;

import com.core.model.User;
import lombok.Data;

/**
 * @author: Huu Sy
 * @Date: 27/04/2024 5:01 SA
 */

@Data
public class AuthenticationResponse {
    private UserResponse user;
    private String token;
    private String refreshToken;
}
