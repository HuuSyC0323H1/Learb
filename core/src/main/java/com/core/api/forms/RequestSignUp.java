package com.core.api.forms;

import lombok.Data;

/**
 * @author: Huu Sy
 * @Date: 25/04/2024 10:49 CH
 */
@Data
public class RequestSignUp {
    private String email;
    private String userName;
    private String oldPassword;
    private String password;
    private String confirmPassword;
    private String fullName;
}
