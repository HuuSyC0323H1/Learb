package com.core.api.forms;

import com.core.infrastructure.constant.ErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseObject {
    private boolean isSuccess = false;
    private Object data;
    private String errorCode = ErrorCode.ERRORS.toString();
    private String message;
    private String requestId;
    private String status = "fail";

    public ResponseObject() {

    }

    public <T> ResponseObject(T obj) {
        if (obj != null) {
            this.isSuccess = true;
            this.status = "success";
            this.data = obj;
            this.errorCode = ErrorCode.SUCCESS.toString();
        }
    }

    public ResponseObject(String errorCode, String message, boolean isSuccess) {
        this.errorCode = errorCode;
        this.message = message;
        this.isSuccess = isSuccess;
    }

}
