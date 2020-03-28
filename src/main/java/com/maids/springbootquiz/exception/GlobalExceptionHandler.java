package com.maids.springbootquiz.exception;

import com.maids.springbootquiz.response.CustomResponse;
import com.maids.springbootquiz.response.CustomResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse errorDetails =
                new ErrorResponse(new Date(), request.getDescription(false));
        CustomResponse<ErrorResponse> stringCustomResponse =
                new CustomResponse<>(errorDetails, CustomResponseStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(stringCustomResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
        ErrorResponse errorDetails =
                new ErrorResponse(new Date(), request.getDescription(false));
        CustomResponse<ErrorResponse> stringCustomResponse =
                new CustomResponse<>(errorDetails, CustomResponseStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(stringCustomResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
