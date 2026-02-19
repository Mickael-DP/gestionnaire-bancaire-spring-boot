package com.micka.banque.exception;

public class SoldeInsuffisantException extends RuntimeException {

     public SoldeInsuffisantException () {
         super("Solde insuffisant pour effectuer ce retrait");
     }
}
