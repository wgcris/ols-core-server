package com.thoughtworks.nho.exception;

import com.thoughtworks.nho.util.ResponseInfo;
import com.thoughtworks.nho.util.ResultCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseInfo handlerException(Exception e) {

        //如果是自定义的异常，返回对应的错误信息
        if (e instanceof InvalidCredentialException) {
            e.printStackTrace();
            InvalidCredentialException invalidCredentialException = (InvalidCredentialException) e;
            return new ResponseInfo(ResultCode.INVALID_CREDENTIAL.getCode(), invalidCredentialException.getMessage());
        } else if (e instanceof UserNotExistException) {
            e.printStackTrace();
            UserNotExistException userNotExistException = (UserNotExistException) e;
            return new ResponseInfo(ResultCode.USER_NOT_EXISTED.getCode(), userNotExistException.getMessage());
        } else if (e instanceof UserExistedException) {
            e.printStackTrace();
            UserExistedException userExistedException = (UserExistedException) e;
            return new ResponseInfo(ResultCode.USER_EXISTED.getCode(), userExistedException.getMessage());
        } else {
            e.printStackTrace();
            return new ResponseInfo(ResultCode.SYSTEM_EXCEPTION.getCode(),"system error!");
        }

    }
}
