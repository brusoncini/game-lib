package com.bruna.gamelib.controller;

import com.bruna.gamelib.dto.JogoRawgDTO;
import com.bruna.gamelib.entity.Jogo;
import com.bruna.gamelib.service.JogoService;
import com.bruna.gamelib.service.RawgService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JogoController {


    private final JogoService jogoService;
    private final RawgService rawgService;

    public JogoController(JogoService jogoService, RawgService rawgService) {
        this.jogoService = jogoService;
        this.rawgService = rawgService;
    }

    @GetMapping("/jogos")
    public List<Jogo> listarJogos() {
        return jogoService.listarJogos();
    }

    @GetMapping("/externo/jogos")
    public List<JogoRawgDTO> buscarJogosExternos(@RequestParam String nome) throws Exception {
        return rawgService.buscarJogosPorNome(nome);
    }

    @GetMapping("/jogo/{id}")
    public ResponseEntity<Jogo> buscarJogoPorId(@PathVariable Long id) {
        return jogoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/jogo")
    public ResponseEntity<Jogo> cadastrarJogo(@RequestBody Jogo jogo) {
        Jogo jogoSalvo = jogoService.salvar(jogo);
        return ResponseEntity.status(201).body(jogoSalvo);
    }

    @PutMapping("/jogo/{id}")
    public ResponseEntity<Jogo> atualizarJogo(@PathVariable Long id, @RequestBody Jogo jogoAtualizado) {
        return jogoService.atualizar(id, jogoAtualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/jogo/{id}")
    public ResponseEntity<String> deletarJogo(@PathVariable Long id) {
        boolean removido = jogoService.deletar(id);

        if (!removido) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/externo/jogo/{id}")
    public ResponseEntity<JogoRawgDTO> buscarJogoExternoPorId(@PathVariable Integer id) throws Exception {
        JogoRawgDTO jogo = rawgService.buscarJogoPorId(id);

        if (jogo == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(jogo);
    }
}