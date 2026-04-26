package com.bruna.gamelib.controller;

import com.bruna.gamelib.dto.JogoRawgDTO;
import com.bruna.gamelib.service.RawgService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/rawg")
public class RawgController {

    private final RawgService rawgService;

    public RawgController(RawgService rawgService) {
        this.rawgService = rawgService;
    }

    @GetMapping("/jogos")
    public List<JogoRawgDTO> buscarJogosPorNome(@RequestParam String nome) throws Exception {
        return rawgService.buscarJogosPorNome(nome);
    }

    @GetMapping("/jogos/{rawgId}")
    public JogoRawgDTO buscarJogoPorId(@PathVariable Integer rawgId) throws IOException, InterruptedException {
        return rawgService.buscarJogoPorId(rawgId);
    }
}