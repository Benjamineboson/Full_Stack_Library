package com.example.library_fullstack.exception;

public class AppResourceNotFoundException extends RuntimeException {
    public AppResourceNotFoundException(String message) {
        super(message);
    }
}
