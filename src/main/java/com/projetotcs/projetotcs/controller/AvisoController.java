package com.projetotcs.projetotcs.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetotcs.projetotcs.model.Aviso;
import com.projetotcs.projetotcs.model.ErrorResponse;
import com.projetotcs.projetotcs.service.AvisosService;
import com.projetotcs.projetotcs.service.SessaoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;







@RestController
@RequestMapping("/avisos")

public class AvisoController {

    @Autowired
    AvisosService avisosService;



    SessaoService sessaoService;

    public AvisoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    
    @PostMapping
    public ResponseEntity<?> cadastrarAviso(@RequestBody Aviso aviso,@RequestHeader(value = "Authorization", required = false) String authorizationHeader ) {

        if(isHeaderValid(authorizationHeader)) if(sessaoService.isAdmin(authorizationHeader.substring(7))==false){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("Você não tem permissão suficiente para performar esta ação"));
        }

        Aviso novoAviso = avisosService.cadastrarAviso(aviso);
        if(aviso.getDescricao().length()>120){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Dados invalidos"));
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body("");
        }
       
    }

  //  @GetMapping
  //  public ResponseEntity<List<Aviso>> listarAvisos() {
  //     List<Aviso> avisos = avisosService.listarAvisos();
  //     return ResponseEntity.ok(avisos);
  // }


    @GetMapping("/{idCategoria}")
    public ResponseEntity<List<Aviso>> listarAvisosPorCategoria(@PathVariable int idCategoria) {
        List<Aviso> avisos = avisosService.buscarPorCategoria(idCategoria);
        return ResponseEntity.ok(avisos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarAviso(@PathVariable int id, @RequestBody Aviso aviso, @RequestHeader(value = "Authorization", required = false) String authorizationHeader ) {

        
        if(isHeaderValid(authorizationHeader)) if(sessaoService.isAdmin(authorizationHeader.substring(7))==false){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("Você não tem permissão suficiente para performar esta ação"));
        }

        Aviso avisoAtualizado = avisosService.atualizarAviso(id, aviso);
        //retorna okay caso o aviso esteja not null;
        if(avisoAtualizado!=null){
            return ResponseEntity.ok("");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Aviso não encontrado"));
        }
       
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarAviso(@PathVariable int id, @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {

        if(isHeaderValid(authorizationHeader)) if(sessaoService.isAdmin(authorizationHeader.substring(7))==false){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("Você não tem permissão suficiente para performar esta ação"));
        }

        if(avisosService.deletarAviso(id)){
            return ResponseEntity.noContent().build();
        
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Aviso não encontrado"));
        }
       
       
    }

    public boolean isHeaderValid(String authorizationHeader) {
       
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return false;
        }
    

        String token = authorizationHeader.substring(7); // Remove o prefixo "Bearer "
    
  
        try {
            sessaoService.validarToken(token); 
            return true;
        } catch (Exception e) {
            return false; 
        }
    }



}
