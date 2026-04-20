package com.bruna.gamelib.controller;

import com.bruna.gamelib.entity.Jogo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JogoController {

    private List<Jogo> listaJogos = new ArrayList<>();

    public JogoController() {
        Jogo jogo1 = new Jogo(1L, "Hollow Knight", "Metroidvania", "PC", "Quero jogar", true);
        Jogo jogo2 = new Jogo(2L, "God of War", "Ação", "PS5", "Jogando", false);
        Jogo jogo3 = new Jogo(3L, "Stardew Valley", "Simulação", "PC", "Zerado", true);

        listaJogos.add(jogo1);
        listaJogos.add(jogo2);
        listaJogos.add(jogo3);
    }

    @GetMapping("/jogo")
    public Jogo buscarJogo() {
        return listaJogos.getFirst();
    }

    @GetMapping("/jogos")
    public List<Jogo> listarJogos() {
        return listaJogos;
    }

    @PostMapping("/jogo")
    public ResponseEntity<Jogo> cadastrarJogo(@RequestBody Jogo jogo) {
        listaJogos.add(jogo);
        return ResponseEntity.status(201).body(jogo);
    }
}