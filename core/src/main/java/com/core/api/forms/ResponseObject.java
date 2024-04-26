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

    public <T> ResponseObject(T obj) {
        processReponseObject(obj);
    }

    public void processReponseObject(Object obj) {
        if (obj != null) {
            this.isSuccess = true;
            this.data = obj;
            this.errorCode = ErrorCode.SUCCESS.toString();
        }
    }

    public ResponseObject(String errorCode, String message, boolean isSuccess) {
        this.errorCode = errorCode;
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public ResponseObject(boolean isSuccess, Object data, String errorCode, String message, String requestId) {
        super();
        this.isSuccess = isSuccess;
        this.data = data;
        this.errorCode = errorCode;
        this.message = message;
        this.requestId = requestId;
    }

    public ResponseObject() {

    }
}
