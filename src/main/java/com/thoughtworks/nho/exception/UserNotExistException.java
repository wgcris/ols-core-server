package com.thoughtworks.nho.exception;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException(String username) {
        super(username);
    }
}
