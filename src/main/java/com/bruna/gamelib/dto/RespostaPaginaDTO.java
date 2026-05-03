package com.bruna.gamelib.dto;

import java.util.List;

public class RespostaPaginaDTO<T> {
    private List<T> conteudo;
    private int paginaAtual;
    private int tamanhoPagina;
    private long totalElementos;
    private int totalPaginas;

    public RespostaPaginaDTO(List<T> conteudo,
                             int paginaAtual,
                             int tamanhoPagina,
                             long totalElementos,
                             int totalPaginas) {
        this.conteudo = conteudo;
        this.paginaAtual = paginaAtual;
        this.tamanhoPagina = tamanhoPagina;
        this.totalElementos = totalElementos;
        this.totalPaginas = totalPaginas;
    }

    public List<T> getConteudo() {
        return conteudo;
    }

    public int getPaginaAtual() {
        return paginaAtual;
    }

    public int getTamanhoPagina() {
        return tamanhoPagina;
    }

    public long getTotalElementos() {
        return totalElementos;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }
}
