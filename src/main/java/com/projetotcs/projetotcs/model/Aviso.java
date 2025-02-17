package com.projetotcs.projetotcs.model;

import jakarta.persistence.*;

@Entity
@Table(name = "avisos")
public class Aviso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int idCategoria;

    private String descricao;

    public Aviso() {
    }

    public Aviso(int idCategoria, String descricao) {
        this.idCategoria = idCategoria;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
