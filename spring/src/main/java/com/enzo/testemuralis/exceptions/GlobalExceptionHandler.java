package com.enzo.testemuralis.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.enzo.testemuralis.dto.Response;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        return ResponseEntity
                .internalServerError()
                .body(Response.error("An unexpected error occurred. Please try again later."));
    }
    @ExceptionHandler({ BadRequestException.class })
    public ResponseEntity<Object> handleBadRequestException(BadRequestException exception) {
        return ResponseEntity
                .badRequest()
                .body(Response.error(exception.getMessage()));
    }
    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<Object> handleIllegalArgumentException(BadRequestException exception) {
        return ResponseEntity
                .badRequest()
                .body(Response.error(exception.getMessage()));
    }
}