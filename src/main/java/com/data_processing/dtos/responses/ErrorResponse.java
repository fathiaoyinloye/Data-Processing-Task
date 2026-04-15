package com.data_processing.dtos.responses;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private String status;
    private String message;
}