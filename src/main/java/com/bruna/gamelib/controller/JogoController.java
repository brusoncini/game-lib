package com.bruna.gamelib.controller;

import com.bruna.gamelib.entity.Jogo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JogoController {

    @GetMapping("/jogo")
    public Jogo buscarJogo() {
        Jogo jogo = new Jogo();

        jogo.setId(1L);
        jogo.setNome("Hollow Knight");
        jogo.setGenero("Metroidvania");
        jogo.setPlataforma("PC");
        jogo.setStatus("Quero jogar");
        jogo.setFavorito(true);

        return jogo;
    }
}