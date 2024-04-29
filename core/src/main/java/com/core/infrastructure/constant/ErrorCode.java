package com.core.infrastructure.constant;

import java.io.Serializable;

/**
 * @author: Huu Sy
 * @Date: 24/04/2024 9:51 CH
 */

public enum ErrorCode implements Serializable {
    ERRORS,
    SUCCESS,
    CHECKSUM_INVALID,
    TOKEN_INVALID,
    EMAIL_HAS_BEEN_CREATED,
    USER_REGISTERED,
    USERNAME_OR_PASSWORD_INVALID,
    PASSWORD_INVALID_FORMAT,
    DUPLICATE_PASSWORD,
    PRODUCT_DOES_NOT_EXIT,
    IS_EMPTY,
}
