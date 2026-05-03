package com.bruna.gamelib.controller;

import com.bruna.gamelib.dto.AvaliacaoJogoDTO;
import com.bruna.gamelib.dto.RespostaPaginaDTO;
import com.bruna.gamelib.entity.Jogo;
import com.bruna.gamelib.enums.StatusJogo;
import com.bruna.gamelib.service.JogoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/jogos")
public class JogoController {

    private final JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @GetMapping
    public RespostaPaginaDTO<Jogo> listarJogos(
            @RequestParam(required = false) StatusJogo status,
            @RequestParam(required = false) Boolean favorito,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Jogo> paginaJogos = jogoService.filtrarJogos(status, favorito, pageable);

        return new RespostaPaginaDTO<>(
                paginaJogos.getContent(),
                paginaJogos.getNumber(),
                paginaJogos.getSize(),
                paginaJogos.getTotalElements(),
                paginaJogos.getTotalPages()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogo> buscarJogoPorId(@PathVariable Long id) {
        return jogoService.buscarJogoPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Jogo salvarJogo(@RequestBody Jogo jogo) {
        return jogoService.salvarJogo(jogo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogo> atualizarJogo(
            @PathVariable Long id,
            @RequestBody Jogo jogo) {
        return jogoService.atualizarJogo(id, jogo).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
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
    public ResponseEntity<Jogo> importarJogoDaRawg(
            @PathVariable Integer rawgId,
            @RequestParam(defaultValue = "QUERO_JOGAR") StatusJogo status,
            @RequestParam(defaultValue = "false") Boolean favorito

    ) throws IOException, InterruptedException {
        Jogo jogoImportado = jogoService.importarJogoDaRawg(rawgId, status, favorito);

        return ResponseEntity.status(201).body(jogoImportado);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Jogo> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusJogo status
    ) {
        return jogoService.atualizarStatus(id, status).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/favorito")
    public ResponseEntity<Jogo> atualizarFavorito(
            @PathVariable Long id,
            @RequestParam Boolean favorito
    ) {
        return jogoService.atualizarFavorito(id, favorito).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/avaliacao")
    public ResponseEntity<Jogo> atualizarAvaliacao(
            @PathVariable Long id,
            @RequestBody AvaliacaoJogoDTO avaliacao
    ) {
        return jogoService.atualizarAvaliacao(id, avaliacao).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}