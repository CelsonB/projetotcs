package com.projetotcs.projetotcs.service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import com.projetotcs.projetotcs.model.Sessao;
import com.projetotcs.projetotcs.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class SessaoService {
    private Map<String, Sessao> sessoesAtivas = new HashMap<>();

    private static final String SECRET_KEY = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
    private final Key key;

    public SessaoService() {
        byte[] secretKeyBytes = Base64.getDecoder().decode(SECRET_KEY);
        this.key = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    
    public String iniciarSessao(Usuario usuario) {
        Sessao sessao = new Sessao(usuario,gerarToken(usuario));
        sessoesAtivas.put(sessao.getToken(), sessao);
        return sessao.getToken();
    }



    public Sessao obterSessao(String id) {
        return sessoesAtivas.get(id);
    }


    public boolean sairSessao(String id) {
        if (sessoesAtivas.containsKey(id)) {
            sessoesAtivas.remove(id);
            return true; 
        }
        return false; 
    }

    public void sairDeTodasSessoes() {
        sessoesAtivas.clear(); 
    }


    
  public String gerarToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .claim("admin", usuario.isAdmin())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Expira em 1 dia
                .signWith(key)
                .compact();
    }

    public String validarToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject(); 
        } catch (JwtException e) {
            throw new RuntimeException("Token inválido ou expirado");
        }
    }


    public boolean isAdmin(String token) {
    try {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key) 
                .build()
                .parseClaimsJws(token)
                .getBody();
      
        Boolean isAdmin = claims.get("admin", Boolean.class); 
        return isAdmin; 
    } catch (JwtException e) {
        throw new RuntimeException("Token inválido ou expirado", e);
    }
}


}