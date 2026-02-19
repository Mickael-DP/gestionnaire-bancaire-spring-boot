package com.micka.banque.exception;

public class CompteNotFoundException extends RuntimeException {
   public CompteNotFoundException(Long id) {
    super("Compte avec l'" + id + " introuvable");
   }
}