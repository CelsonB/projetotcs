package com.projetotcs.projetotcs.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetotcs.projetotcs.model.Aviso;
import com.projetotcs.projetotcs.model.ErrorResponse;
import com.projetotcs.projetotcs.service.AvisosService;
import com.projetotcs.projetotcs.service.SessaoService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;







@RestController
@RequestMapping("/avisos")

public class AvisoController {

    AvisosService avisosService;

    public AvisoController(AvisosService avisosService) {
        this.avisosService = avisosService;
    }

    
    @PostMapping
    public ResponseEntity<Aviso> cadastrarAviso(@RequestBody Aviso aviso) {
        Aviso novoAviso = avisosService.cadastrarAviso(aviso);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAviso);
    }

    @GetMapping
    public ResponseEntity<List<Aviso>> listarAvisos() {
        List<Aviso> avisos = avisosService.listarAvisos();
        return ResponseEntity.ok(avisos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aviso> atualizarAviso(@PathVariable Long id, @RequestBody Aviso aviso) {
        Aviso avisoAtualizado = avisosService.atualizarAviso(id, aviso);
        return ResponseEntity.ok(avisoAtualizado);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAviso(@PathVariable Long id) {
        avisosService.deletarAviso(id);
        return ResponseEntity.noContent().build();
    }


}
