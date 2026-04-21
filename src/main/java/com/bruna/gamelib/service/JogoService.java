package com.bruna.gamelib.service;

import com.bruna.gamelib.entity.Jogo;
import com.bruna.gamelib.repository.JogoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogoService {

    private final JogoRepository jogoRepository;

    public JogoService(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;
    }

    public List<Jogo> listarJogos() {
        return jogoRepository.findAll();
    }

    public Optional<Jogo> buscarPorId(Long id) {
        return jogoRepository.findById(id);
    }

    public Jogo salvar(Jogo jogo) {
        return jogoRepository.save(jogo);
    }

    public Optional<Jogo> atualizar(Long id, Jogo jogoAtualizado) {
        return jogoRepository.findById(id).map(jogo -> {
            jogo.setNome(jogoAtualizado.getNome());
            jogo.setGenero(jogoAtualizado.getGenero());
            jogo.setPlataforma(jogoAtualizado.getPlataforma());
            jogo.setStatus(jogoAtualizado.getStatus());
            jogo.setFavorito(jogoAtualizado.getFavorito());

            return jogoRepository.save(jogo);
        });
    }

    public boolean deletar(Long id) {
        if (!jogoRepository.existsById(id)) {
            return false;
        }

        jogoRepository.deleteById(id);
        return true;
    }

}
