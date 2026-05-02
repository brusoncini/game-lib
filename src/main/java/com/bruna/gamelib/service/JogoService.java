package com.bruna.gamelib.service;

import com.bruna.gamelib.dto.AvaliacaoJogoDTO;
import com.bruna.gamelib.dto.JogoRawgDTO;
import com.bruna.gamelib.entity.Jogo;
import com.bruna.gamelib.enums.StatusJogo;
import com.bruna.gamelib.exception.JogoJaImportadoException;
import com.bruna.gamelib.exception.NotaInvalidaException;
import com.bruna.gamelib.repository.JogoRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class JogoService {

    private final JogoRepository jogoRepository;
    private final RawgService rawgService;

    public JogoService(JogoRepository jogoRepository, RawgService rawgService) {
        this.jogoRepository = jogoRepository;
        this.rawgService = rawgService;
    }

    public List<Jogo> listarJogos() {
        return jogoRepository.findAll();
    }

    public Optional<Jogo> buscarJogoPorId(Long id) {
        return jogoRepository.findById(id);
    }

    public Jogo salvarJogo(Jogo jogo) {
        return jogoRepository.save(jogo);
    }

    public Optional<Jogo> atualizarJogo(Long id, Jogo jogoAtualizado) {
        return jogoRepository.findById(id).map(jogo -> {
            jogo.setNome(jogoAtualizado.getNome());
            jogo.setGenero(jogoAtualizado.getGenero());
            jogo.setPlataforma(jogoAtualizado.getPlataforma());
            jogo.setStatus(jogoAtualizado.getStatus());
            jogo.setFavorito(jogoAtualizado.getFavorito());
            jogo.setRawgId(jogoAtualizado.getRawgId());

            return jogoRepository.save(jogo);
        });
    }

    public boolean deletarJogo(Long id) {
        if (!jogoRepository.existsById(id)) {
            return false;
        }

        jogoRepository.deleteById(id);
        return true;
    }

    public Jogo importarJogoDaRawg(Integer rawgId, StatusJogo status, Boolean favorito) throws IOException, InterruptedException {
        Optional<Jogo> jogoExistente = jogoRepository.findByRawgId(rawgId);

        if (jogoExistente.isPresent()) {
            throw new JogoJaImportadoException("Este jogo já foi importado para a biblioteca.");
        }


        JogoRawgDTO jogoRawg = rawgService.buscarJogoPorId(rawgId);

        Jogo jogo = new Jogo();

        jogo.setRawgId(rawgId);
        jogo.setNome(jogoRawg.getNome());
        jogo.setGenero(jogoRawg.getGeneros());
        jogo.setPlataforma(jogoRawg.getPlataformas());
        jogo.setStatus(status);
        jogo.setFavorito(favorito);

        return jogoRepository.save(jogo);
    }

    public List<Jogo> filtrarJogos(StatusJogo status, Boolean favorito) {
        if (status != null && favorito != null) {
            return jogoRepository.findByStatusAndFavorito(status, favorito);
        }

        if (status != null) {
            return jogoRepository.findByStatus(status);
        }

        if (favorito != null) {
            return jogoRepository.findByFavorito(favorito);
        }

        return jogoRepository.findAll();
    }

    public Optional<Jogo> atualizarStatus(Long id, StatusJogo status) {
        return jogoRepository.findById(id).map(jogo -> {
            jogo.setStatus(status);
            return jogoRepository.save(jogo);
        });
    }

    public Optional<Jogo> atualizarFavorito(Long id, Boolean favorito) {
        return jogoRepository.findById(id).map(jogo -> {
            jogo.setFavorito(favorito);
            return jogoRepository.save(jogo);
        });
    }

    private void validarNotaPessoal(Integer notaPessoal) {
        if (notaPessoal == null) {
            return;
        }

        if (notaPessoal < 0 || notaPessoal > 10) {
            throw new NotaInvalidaException("A nota pessoal deve estar entre 0 e 10.");
        }
    }

    public Optional<Jogo> atualizarAvaliacao(Long id, AvaliacaoJogoDTO avaliacao) {
        validarNotaPessoal(avaliacao.getNotaPessoal());

        return jogoRepository.findById(id).map(jogo -> {

            if (avaliacao.getNotaPessoal() != null) {
                jogo.setNotaPessoal(avaliacao.getNotaPessoal());
            }

            if (avaliacao.getComentario() != null) {
                jogo.setComentario(avaliacao.getComentario());
            }

            return jogoRepository.save(jogo);
        });
    }
}
