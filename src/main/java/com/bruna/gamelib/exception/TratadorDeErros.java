package com.bruna.gamelib.exception;

import com.bruna.gamelib.dto.RespostaErro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(JogoJaImportadoException.class)
    public ResponseEntity<RespostaErro> tratarJogoJaImportado(JogoJaImportadoException erro) {
        RespostaErro resposta = new RespostaErro(
                409,
                "Conflito",
                erro.getMessage()
        );

        return ResponseEntity.status(409).body(resposta);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<RespostaErro> tratarParametroInvalido(MethodArgumentTypeMismatchException erro) {
        String mensagem = "Algum parametro enviado esta em formato invalido.";

        if ("status".equals(erro.getName())) {
            mensagem = "Status invalido. Use: QUERO_JOGAR, JOGANDO, JOGADO, ZERADO ou ABANDONADO.";
        }

        RespostaErro resposta = new RespostaErro(
                400,
                "Parametro invalido",
                mensagem
        );

        return ResponseEntity.badRequest().body(resposta);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespostaErro> tratarErroGenerico(Exception erro) {
        RespostaErro resposta = new RespostaErro(
                500,
                "Erro interno",
                "Ocorreu um erro inesperado no servidor."
        );

        return ResponseEntity.status(500).body(resposta);
    }

    @ExceptionHandler(NotaInvalidaException.class)
    public ResponseEntity<RespostaErro> tratarNotaInvalida(NotaInvalidaException erro) {
        RespostaErro resposta = new RespostaErro(
                400,
                "Nota invalida",
                erro.getMessage()
        );

        return ResponseEntity.badRequest().body(resposta);
    }
}