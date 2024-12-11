package com.projetotcs.projetotcs.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.projetotcs.projetotcs.model.Sessao;
import com.projetotcs.projetotcs.model.Usuario;

@Service
public class SessaoService {
    private Map<String, Sessao> sessoesAtivas = new HashMap<>();

    public String iniciarSessao(Usuario usuario) {
        Sessao sessao = new Sessao(usuario);
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
}