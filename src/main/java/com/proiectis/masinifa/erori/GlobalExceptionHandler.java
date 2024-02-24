package com.proiectis.masinifa.erori;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException() {
        // In acest caz, cand o ResourceNotFoundException este aruncata, se returneaza "error".
        // Intr-o aplicatie Spring, acest lucru ar putea directiona catre o pagina de eroare personalizata.
        return "error";
    }
}
