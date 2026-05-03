package com.bruna.gamelib.repository;

import com.bruna.gamelib.entity.Jogo;
import com.bruna.gamelib.enums.StatusJogo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
    Page<Jogo> findByStatus(StatusJogo status, Pageable pageable);

    Page<Jogo> findByFavorito(Boolean favorito, Pageable pageable);

    Page<Jogo> findByStatusAndFavorito(StatusJogo status, Boolean favorito, Pageable pageable);

    Optional<Jogo> findByRawgId(Integer rawgId);

    boolean existsByRawgId(Integer rawgId);
}