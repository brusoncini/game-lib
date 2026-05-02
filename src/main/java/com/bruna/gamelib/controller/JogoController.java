package com.bruna.gamelib.controller;

import com.bruna.gamelib.entity.Jogo;
import com.bruna.gamelib.enums.StatusJogo;
import com.bruna.gamelib.service.JogoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/jogos")
public class JogoController {

    private final JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @GetMapping
    public List<Jogo> listarJogos(
            @RequestParam(required = false) StatusJogo status,
            @RequestParam(required = false) Boolean favorito
    ) {
        return jogoService.filtrarJogos(status, favorito);
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

    @PostMapping("/importar/{rawgId}")
    public Jogo importarJogoDaRawg(
            @PathVariable Integer rawgId,
            @RequestParam(defaultValue = "QUERO_JOGAR") StatusJogo status,
            @RequestParam(defaultValue = "false") Boolean favorito
    ) throws IOException, InterruptedException {
        return jogoService.importarJogoDaRawg(rawgId, status, favorito);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Jogo> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusJogo status
    ) {
        return jogoService.atualizarStatus(id, status)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/favorito")
    public ResponseEntity<Jogo> atualizarFavorito(
            @PathVariable Long id,
            @RequestParam Boolean favorito
    ) {
        return jogoService.atualizarFavorito(id, favorito)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}