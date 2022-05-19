package com.study.controller;

import com.study.dto.ErrorDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleValidationException() {
        log.error("ERRO DE USUARIO ");
        var errorDto =
                ErrorDto.builder()
                .codigoErro(HttpStatus.BAD_REQUEST.value())
                .mensagem("Formato da mensagem invalido")
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> handleException(Exception e) {
        log.error("Argumento ilegal: " + e);
        var erroDto =
            ErrorDto.builder()
                    .mensagem(e.getMessage())
                    .codigoErro(999)
                    .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erroDto);
    }

}
