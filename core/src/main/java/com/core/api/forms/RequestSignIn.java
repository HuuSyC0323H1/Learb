package com.core.api.forms;

import lombok.Data;

/**
 * @author: Huu Sy
 * @Date: 25/04/2024 10:49 CH
 */
@Data
public class RequestSignIn {
    private String userName;
    private String password;
    private String fullName;
}
