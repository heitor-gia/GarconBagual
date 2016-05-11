package com.heitor.garconbagual.model;

import android.graphics.Bitmap;

/**
 * Created by heito on 13/10/2015.
 */
public class Produto {
    private String nome;
    private Double preco;
    private Integer id = null;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
