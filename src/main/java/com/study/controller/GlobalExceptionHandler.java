package com.study.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;


@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { ConstraintViolationException.class })
    public ResponseEntity<String> handleRuntimeException(RuntimeException re) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("ERROR: " + re.getMessage());
    }

}
