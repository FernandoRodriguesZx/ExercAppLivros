package com.example.opet.exercapplivros;

/**
 * Created by Bianca e Fernando on 28/03/2018.
 */

public class LivroLista {
    private String titulo;
    private double edicao;
    private double valor_unitário;
    private boolean favorito;

    public LivroLista() {
    }

    public LivroLista(String titulo, double edicao, double valor_unitário, boolean favorito) {
        this.titulo = titulo;
        this.edicao = edicao;
        this.valor_unitário = valor_unitário;
        this.favorito = favorito;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getEdicao() {
        return edicao;
    }

    public void setEdicao(double edicao) {
        this.edicao = edicao;
    }

    public double getValor_unitário() {
        return valor_unitário;
    }

    public void setValor_unitário(double valor_unitário) {
        this.valor_unitário = valor_unitário;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean vendido) {
        this.favorito = favorito;
    }
}
