package com.core.infrastructure.exception;

import java.util.List;

public class ExceptionResponse<T> {
    private List<T> errors;

    public List<T> getErrors() {
        return errors;
    }

    public void setErrors(List<T> errors) {
        this.errors = errors;
    }
}
