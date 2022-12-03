package com.dementiev.webshop.exception;

public class InvalidProductTypeException extends RuntimeException{

    public InvalidProductTypeException(String message) {
        super(message);
    }
}
