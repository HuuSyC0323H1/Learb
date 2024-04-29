package com.core.utils;

import com.core.api.forms.ResponseObject;
import com.core.infrastructure.constant.ErrorCode;
import com.core.infrastructure.constant.LanguageCode;
import com.core.infrastructure.exception.NVException;
import com.core.repositories.MsTemplateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: Huu Sy
 * @Date: 24/04/2024 9:02 CH
 */

@RestControllerAdvice
@Slf4j
public class WebRestControllerAdvice {

    @Autowired
    private MsTemplateRepository msTemplateRepository;

    @ExceptionHandler(NVException.class)
    public ResponseEntity<ResponseObject> nvExceptionHandle(NVException ex) {
        String langCode = LanguageCode.VI.getValue();
        ResponseObject result = new ResponseObject();
        result.setErrorCode(ex.getMessage());
        result.setMessage(msTemplateRepository.getByCode(ex.getMessage(), langCode));
        if (ex.getMessage().equals(ErrorCode.CHECKSUM_INVALID.toString()) || ex.getMessage().equals(ErrorCode.TOKEN_INVALID.toString())) {
            log.info("" + ex.getMessage());
            return new ResponseEntity(result, HttpStatus.UNAUTHORIZED);
        }
        log.info("" + ex.getMessage());
        return new ResponseEntity(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseObject> handleAuthenticationException(AuthenticationException ex) {
        ResponseObject result = new ResponseObject();
        result.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

    @ExceptionHandler(AuthorizationServiceException.class)
    public ResponseEntity<ResponseObject> handleAuthorizationException(AuthorizationServiceException ex) {
        ResponseObject result = new ResponseObject();
        result.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseObject> handleGenericException(Exception ex) {
        ResponseObject result = new ResponseObject();
        result.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }
}
