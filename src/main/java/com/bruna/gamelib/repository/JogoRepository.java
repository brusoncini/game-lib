package com.bruna.gamelib.repository;

import com.bruna.gamelib.entity.Jogo;
import com.bruna.gamelib.enums.StatusJogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
    List<Jogo> findByStatus(StatusJogo status);

    List<Jogo> findByFavorito(Boolean favorito);

    List<Jogo> findByStatusAndFavorito(StatusJogo status, Boolean favorito);

    Optional<Jogo> findByRawgId(Integer rawgId);

    boolean existsByRawgId(Integer rawgId);
}