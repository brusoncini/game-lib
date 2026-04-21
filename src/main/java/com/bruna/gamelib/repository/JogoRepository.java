package com.bruna.gamelib.repository;

import com.bruna.gamelib.entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
}