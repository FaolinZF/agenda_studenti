package com.nanosoft.agenda_studenti;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AppuntamentoPerTipoAppuntamentiNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(AppuntamentoPerTipoAppuntamentiNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String appuntamentoPerTipoAppuntamentiNotFoundHandler(AppuntamentoPerTipoAppuntamentiNotFoundException ex) {
        return ex.getMessage();
    }
}
