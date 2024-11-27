package com.projetotcs.projetotcs.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.projetotcs.projetotcs.model.Sessao;
import com.projetotcs.projetotcs.model.Usuario;

@Service
public class SessaoService {
    private Map<UUID, Sessao> sessoesAtivas = new HashMap<>();

    public UUID iniciarSessao(Usuario usuario) {
        Sessao sessao = new Sessao(usuario);
        sessoesAtivas.put(sessao.getId(), sessao);
        return sessao.getId();
    }

    public Sessao obterSessao(UUID id) {
        return sessoesAtivas.get(id);
    }


    public boolean sairSessao(UUID id) {
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