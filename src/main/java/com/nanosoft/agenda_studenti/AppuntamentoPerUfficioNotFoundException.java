package com.nanosoft.agenda_studenti;

public class AppuntamentoPerUfficioNotFoundException extends RuntimeException {

    AppuntamentoPerUfficioNotFoundException(String message) {
        super(message);
    }
}