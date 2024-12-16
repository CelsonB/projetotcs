package com.projetotcs.projetotcs.model;

import java.util.Date;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Base64;
import java.security.Key;






public class Sessao {
    
    private String token; // Identificador único da sessão
    private Usuario usuario; // Usuário associado à sessão
    private long dataCriacao; // Timestamp de criação da sessão

    public Sessao(Usuario usuario, String token) {

        this.token = token; // Gera um UUID único
        this.usuario = usuario;
        this.dataCriacao = System.currentTimeMillis(); // Armazena o timestamp atual
    }
   
    
    public String getToken() {
        return token;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public long getDataCriacao() {
        return dataCriacao;
    }


    @Override
    public String toString() {
        return "Sessao [id=" + token + ", usuario=" + usuario + ", dataCriacao=" + dataCriacao + "]";
    }
}