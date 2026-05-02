package com.bruna.gamelib.entity;

import com.bruna.gamelib.enums.StatusJogo;
import jakarta.persistence.*;

@Entity
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Integer rawgId;

    private String nome;
    private String genero;
    private String plataforma;
    private Boolean favorito;

    @Enumerated(EnumType.STRING)
    private StatusJogo status;

    public Jogo() {
    }

    public Jogo(Long id, String nome, String genero, String plataforma, Boolean favorito, StatusJogo status) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.plataforma = plataforma;
        this.favorito = favorito;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRawgId() {
        return rawgId;
    }

    public void setRawgId(Integer rawgId) {
        this.rawgId = rawgId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public StatusJogo getStatus() {
        return status;
    }

    public void setStatus(StatusJogo status) {
        this.status = status;
    }

    public Boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(Boolean favorito) {
        this.favorito = favorito;
    }
}
