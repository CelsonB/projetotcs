package com.projetotcs.projetotcs.model;

public class Aviso {
    
    private int idCategoria;
    private String descricao;
    
    
    
    public Aviso(int idCategoria, String descricao) {
        this.idCategoria = idCategoria;
        this.descricao = descricao;
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
