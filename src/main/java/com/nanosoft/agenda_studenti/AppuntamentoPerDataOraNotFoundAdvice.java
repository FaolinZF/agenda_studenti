package com.nanosoft.agenda_studenti;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AppuntamentoPerDataOraNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(AppuntamentoPerDataOraNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String appuntamentoPerDataOraNotFoundHandler(AppuntamentoPerDataOraNotFoundException ex) {
        return ex.getMessage();
    }
}
