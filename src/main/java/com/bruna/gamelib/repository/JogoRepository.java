package com.bruna.gamelib.repository;

import com.bruna.gamelib.entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
    List<Jogo> findByStatus(String status);

    List<Jogo> findByFavorito(Boolean favorito);

    List<Jogo> findByStatusAndFavorito(String status, Boolean favorito);
}