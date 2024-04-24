package com.core.infrastructure.exception;

import com.core.infrastructure.constant.ErrorCode;

public class NVException extends BaseException {

    public NVException(String message) {
        super(message);
    }

    public NVException(ErrorCode errorCode) {
        super(errorCode.toString());
    }

    public NVException(ErrorCode errorCode, Throwable t) {
        super(errorCode.toString(), t);
    }

    public NVException(ErrorCode errorCode, Throwable t, Object[] arrs) {
        super(errorCode.toString(), t, arrs);
    }

    public NVException(String message, Object[] arrs) {
        super(message, arrs);
    }

    public NVException(String message, Throwable t, Object[] arrs) {
        super(message, t, arrs);
    }
}

