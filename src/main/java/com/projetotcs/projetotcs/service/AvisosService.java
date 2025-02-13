package com.projetotcs.projetotcs.service;

import com.projetotcs.projetotcs.model.Aviso;
import com.projetotcs.projetotcs.repository.AvisosRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AvisosService {

    private final AvisosRepository avisoRepository;

    public AvisosService(AvisosRepository avisoRepository) {
        this.avisoRepository = avisoRepository;
    }

    public Aviso cadastrarAviso(Aviso aviso) {
        return avisoRepository.save(aviso);
    }

    public List<Aviso> listarAvisos() {
        return avisoRepository.findAll();
    }

    public Optional<Aviso> buscarAvisoPorId(Long id) {
        return avisoRepository.findById(id);
    }

    public Aviso atualizarAviso(Long id, Aviso avisoAtualizado) {
        return avisoRepository.findById(id)
            .map(aviso -> {
                aviso.setDescricao(avisoAtualizado.getDescricao());
                return avisoRepository.save(aviso);
            })
            .orElseThrow(() -> new RuntimeException("Aviso não encontrado"));
    }

    public void deletarAviso(Long id) {
        avisoRepository.deleteById(id);
    }
}
