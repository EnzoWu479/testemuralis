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
                .badRequest()
                .body(Response.error(exception.getMessage()));
    }
}