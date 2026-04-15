package com.data_processing.exceptions;

import com.data_processing.dtos.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ErrorResponse> handleRestClientException(RestClientException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)  // 502
                .body(new ErrorResponse("error", "External API unavailable"));
    }
    @ExceptionHandler(GenderApiException.class)
    public ResponseEntity<ErrorResponse> handleGenderApiException(GenderApiException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErrorResponse("error", ex.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMisingName(MissingServletRequestParameterException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("error", "Name is missing"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("error", "An unexpected error occurred"));
    }




}