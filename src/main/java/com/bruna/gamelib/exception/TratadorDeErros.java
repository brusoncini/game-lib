package com.bruna.gamelib.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(JogoJaImportadoException.class)
    public ResponseEntity<String> tratarJogoJaImportado(JogoJaImportadoException erro) {
        return ResponseEntity.status(409).body(erro.getMessage());
    }
}