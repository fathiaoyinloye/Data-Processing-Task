package com.data_processing.exceptions;

public class InvalidNameException extends RuntimeException{
    public InvalidNameException(String message) {
        super("Name is invalid and must be a string");
    }
}
