package com.micka.banque.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CompteNotFoundException.class)
    public ResponseEntity<String> handleCompteNotFound(CompteNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(SoldeInsuffisantException.class)
    public  ResponseEntity<String> handleSoldeInsuffisant(SoldeInsuffisantException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
