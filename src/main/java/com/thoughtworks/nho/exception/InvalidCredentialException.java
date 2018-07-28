package com.thoughtworks.nho.exception;

public class InvalidCredentialException extends RuntimeException {
    public InvalidCredentialException(String message){
        super(message);
    }

    public InvalidCredentialException() {
        super();
    }
}
