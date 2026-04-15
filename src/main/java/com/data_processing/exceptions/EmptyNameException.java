package com.data_processing.exceptions;

public class EmptyNameException extends RuntimeException {
    public EmptyNameException(String message) {
        super("Name must not be empty");
    }
}
