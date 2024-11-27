package com.projetotcs.projetotcs.model;

import java.util.UUID;

public class Sessao {
    
    private UUID id; // Identificador único da sessão
    private Usuario usuario; // Usuário associado à sessão
    private long dataCriacao; // Timestamp de criação da sessão

    public Sessao(Usuario usuario) {
        this.id = UUID.randomUUID(); // Gera um UUID único
        this.usuario = usuario;
        this.dataCriacao = System.currentTimeMillis(); // Armazena o timestamp atual
    }

    public UUID getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public long getDataCriacao() {
        return dataCriacao;
    }

    @Override
    public String toString() {
        return "Sessao [id=" + id + ", usuario=" + usuario + ", dataCriacao=" + dataCriacao + "]";
    }
}