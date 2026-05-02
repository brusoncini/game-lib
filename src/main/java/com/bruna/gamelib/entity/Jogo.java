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

    @Column(length = 1000)
    private String comentario;

    private String nome;
    private String genero;
    private String plataforma;
    private Boolean favorito;
    private Integer notaPessoal;

    @Enumerated(EnumType.STRING)
    private StatusJogo status;

    public Jogo() {
    }

    public Jogo(Long id, Integer rawgId, String nome, String genero, String plataforma,
                Boolean favorito, StatusJogo status, Integer notaPessoal, String comentario) {
        this.id = id;
        this.rawgId = rawgId;
        this.nome = nome;
        this.genero = genero;
        this.plataforma = plataforma;
        this.favorito = favorito;
        this.status = status;
        this.notaPessoal = notaPessoal;
        this.comentario = comentario;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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

    public Boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(Boolean favorito) {
        this.favorito = favorito;
    }

    public Integer getNotaPessoal() {
        return notaPessoal;
    }

    public void setNotaPessoal(Integer notaPessoal) {
        this.notaPessoal = notaPessoal;
    }

    public StatusJogo getStatus() {
        return status;
    }

    public void setStatus(StatusJogo status) {
        this.status = status;
    }
}
