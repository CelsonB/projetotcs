package com.projetotcs.projetotcs.model;

import java.util.Date;
import java.util.UUID;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Sessao {
    
    private String token; // Identificador único da sessão
    private Usuario usuario; // Usuário associado à sessão
    private long dataCriacao; // Timestamp de criação da sessão

    public Sessao(Usuario usuario) {
        this.token = gerarToken(usuario); // Gera um UUID único
        this.usuario = usuario;
        this.dataCriacao = System.currentTimeMillis(); // Armazena o timestamp atual
    }

    private static final String SECRET_KEY = "sua_chave_secreta";



    private String gerarToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getEmail()) // ID do usuário
                .claim("admin", usuario.isAdmin()) // Adiciona se é admin
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Expira em 1 dia
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
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

    public boolean isTokenValido(String token2){
    System.out.println(this.token +"=="+token2);
    boolean op2 = this.token.equals(token2);
        System.out.println(op2);
        return  op2;
    }

    @Override
    public String toString() {
        return "Sessao [id=" + token + ", usuario=" + usuario + ", dataCriacao=" + dataCriacao + "]";
    }
}