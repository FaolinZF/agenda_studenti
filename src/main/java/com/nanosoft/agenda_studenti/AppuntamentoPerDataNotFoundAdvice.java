package com.nanosoft.agenda_studenti;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AppuntamentoPerDataNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(AppuntamentoPerDataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String appuntamentoPerDataNotFoundHandler(AppuntamentoPerDataNotFoundException ex) {
        return ex.getMessage();
    }
}
