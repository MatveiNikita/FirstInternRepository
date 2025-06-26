package com.example.firstinternrepository.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionResponse> handResponseException(ResponseStatusException responseStatusException, HttpServletRequest servletRequest){
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), responseStatusException.getStatusCode().toString(),
                responseStatusException.getReason(), servletRequest.getRequestURI());

        return ResponseEntity.status(responseStatusException.getStatusCode()).body(response);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ExceptionResponse> handDuplicateKeyException(DuplicateKeyException duplicateKeyException ,HttpServletRequest servletRequest){
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.CONFLICT.toString(), duplicateKeyException.getMessage(),
                servletRequest.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
