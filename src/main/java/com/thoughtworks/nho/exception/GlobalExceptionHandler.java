package com.thoughtworks.nho.exception;

import com.thoughtworks.nho.util.ResponseInfo;
import com.thoughtworks.nho.util.ResultCode;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({InvalidCredentialException.class, UserNotExistException.class, UserExistedException.class})
    public ResponseInfo handlerCustomizeException(Exception e) {
        log.debug(e.getMessage());
        return new ResponseInfo(ResultCode.CUSTOMIZE_ERROR.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseInfo handlerSystemException(Exception e) {
        log.error(e.getMessage());
        return new ResponseInfo(ResultCode.SYSTEM_EXCEPTION.getCode(), "system error");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseInfo handlerBadCredentialsException(Exception e) {
        log.error(e.getMessage());
        return new ResponseInfo(ResultCode.SYSTEM_EXCEPTION.getCode(), "登录信息有误");
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseInfo handleInternalException(Exception e) {
        InternalAuthenticationServiceException ex = (InternalAuthenticationServiceException) e;
        return new ResponseInfo(ResultCode.CUSTOMIZE_ERROR.getCode(), ex.getCause().getMessage());
    }


}
