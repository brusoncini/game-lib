package com.bruna.gamelib.controller;

import com.bruna.gamelib.entity.Jogo;
import com.bruna.gamelib.service.JogoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jogos")
public class JogoController {

    private final JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @GetMapping
    public List<Jogo> listarJogos() {
        return jogoService.listarJogos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogo> buscarJogoPorId(@PathVariable Long id) {
        return jogoService.buscarJogoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Jogo salvarJogo(@RequestBody Jogo jogo) {
        return jogoService.salvarJogo(jogo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogo> atualizarJogo(@PathVariable Long id, @RequestBody Jogo jogo) {
        return jogoService.atualizarJogo(id, jogo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarJogo(@PathVariable Long id) {
        boolean deletou = jogoService.deletarJogo(id);

        if (deletou) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}