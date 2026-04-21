package com.bruna.gamelib.controller;

import com.bruna.gamelib.entity.Jogo;
import com.bruna.gamelib.repository.JogoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JogoController {

    private List<Jogo> listaJogos = new ArrayList<>();

    private final JogoRepository jogoRepository;

    public JogoController(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;
    }

    @GetMapping("/jogo")
    public Jogo buscarJogo() {
        return listaJogos.getFirst();
    }

    @GetMapping("/jogo/{id}")
    public ResponseEntity<Jogo> buscarJogoPorId(@PathVariable Long id) {
        return jogoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/jogos")
    public List<Jogo> listarJogos() {
        return jogoRepository.findAll();
    }

    @PostMapping("/jogo")
    public ResponseEntity<Jogo> cadastrarJogo(@RequestBody Jogo jogo) {
        Jogo jogoSalvo = jogoRepository.save(jogo);
        return ResponseEntity.status(201).body(jogoSalvo);
    }

    @PutMapping("/jogo/{id}")
    public ResponseEntity<Jogo> atualizarJogo(@PathVariable Long id, @RequestBody Jogo jogoAtualizado) {
        return jogoRepository.findById(id).map(jogo -> {
            jogo.setNome(jogoAtualizado.getNome());
            jogo.setGenero(jogoAtualizado.getGenero());
            jogo.setPlataforma(jogoAtualizado.getPlataforma());
            jogo.setStatus(jogoAtualizado.getStatus());
            jogo.setFavorito(jogoAtualizado.getFavorito());

            Jogo jogoSalvo = jogoRepository.save(jogo);
            return ResponseEntity.ok(jogoSalvo);

        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/jogo/{id}")
    public ResponseEntity<String> deletarJogo(@PathVariable Long id) {
        if (!jogoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        jogoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}