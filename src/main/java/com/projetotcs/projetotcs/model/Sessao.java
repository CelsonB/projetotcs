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

    public Sessao(Usuario usuario) {

        this.token = gerarToken(usuario); // Gera um UUID único
        this.usuario = usuario;
        this.dataCriacao = System.currentTimeMillis(); // Armazena o timestamp atual
    }

    private static final String SECRET_KEY = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";



    private String gerarToken(Usuario usuario) {




      byte[] secretKeyBytes = Base64.getDecoder().decode(SECRET_KEY);
      Key key = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());

        // Gera o token JWT
        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .claim("admin", usuario.isAdmin())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 dia
                .signWith(key) // Passa apenas o objeto Key
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