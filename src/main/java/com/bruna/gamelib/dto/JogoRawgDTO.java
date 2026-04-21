package com.bruna.gamelib.dto;

import java.util.ArrayList;
import java.util.List;

public class JogoRawgDTO {

    private Integer id;
    private String nome;
    private String dataLancamento;
    private Double nota;
    private String generos;
    private String plataformas;

    public JogoRawgDTO() {
    }

    public JogoRawgDTO(Integer id, String nome, String dataLancamento, Double nota, String generos, String plataformas) {
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.nota = nota;
        this.generos = generos;
        this.plataformas = plataformas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getGeneros() {
        return generos;
    }

    public void setGeneros(String genero) {
        this.generos = genero;
    }

    public String getPlataformas() {
        return plataformas;
    }

    public void setPlataformas(String plataformas) {
        this.plataformas = plataformas;
    }
}
