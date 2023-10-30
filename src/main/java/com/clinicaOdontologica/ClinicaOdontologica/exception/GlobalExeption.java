package com.clinicaOdontologica.ClinicaOdontologica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class GlobalExeption {
    @ExceptionHandler({ResourceNotFoundExeption.class})

    public ResponseEntity<String> tratamientoResourceNotFoundExeption(ResourceNotFoundExeption rnft){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnft.getMessage());
    }

}
