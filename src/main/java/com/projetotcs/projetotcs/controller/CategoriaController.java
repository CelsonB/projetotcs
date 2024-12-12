package com.projetotcs.projetotcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetotcs.projetotcs.service.CategoriaService;
import com.projetotcs.projetotcs.service.SessaoService;
import com.projetotcs.projetotcs.model.ErrorResponse;
import com.projetotcs.projetotcs.model.Sessao;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;







@RestController
@RequestMapping("/categorias")

public class CategoriaController {
  
    @Autowired
    CategoriaService categoriaService;

    @Autowired
    SessaoService sessaoService;

    @PostMapping("")
    public ResponseEntity<?> cadastrarCategoria(@RequestBody String nome, @RequestHeader(value = "Authorization", required = false) String authorizationHeader ) {
        
        if(!sessaoService.obterSessao(authorizationHeader).getUsuario().isAdmin()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("Você não tem permissão suficiente para performar esta ação"));
        }
        
        if(categoriaService.cadastrarCategoria(nome)){
            return ResponseEntity.ok("");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Dados invalidos"));
        }
   

   
    }

    @GetMapping("")
    public ResponseEntity<?> listarCategorias(@RequestHeader(value = "Authorization", required = false) String authorizationHeader ) {
        
        if(isHeaderValid(authorizationHeader)){
            return ResponseEntity.ok(categoriaService.listarCategorias());
        }else
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("Você não está logado"));
        }

    }
    

    @PutMapping("/{id}")
    public ResponseEntity<?> pesquisarCategoriaPorId(@PathVariable int id, @RequestHeader(value = "Authorization", required = false) String authorizationHeader ) {

        if(isHeaderValid(authorizationHeader)){
            return ResponseEntity.ok(categoriaService.pesquisarCategoriaPorId(id));
        }else
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("Você não está logado"));
        }
    
    }

    @DeleteMapping
    public ResponseEntity<?> deletarCategoria(@PathVariable int id, @RequestHeader(value = "Authorization", required = false) String authorizationHeader ){


        if(!sessaoService.obterSessao(authorizationHeader).getUsuario().isAdmin()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("Você não tem permissão suficiente para performar esta ação"));
        }

        if(categoriaService.pesquisarCategoriaPorId(id)==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Categoria não encontrada"));
        }else{
            return ResponseEntity.ok("");
        }

    }


    /**
     * eu posso tirar esse codigo e colocar em algum lugar, provavelmente vou usar na proxima entregar tambem. 
     * 
     * 
     */
    public boolean isHeaderValid(String authorizationHeader ){
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return false;
        }
        
        String token = authorizationHeader.substring(7); 

        if (!sessaoService.obterSessao(token).isTokenValido(token)) {
            return false;
        }
        return true;
    }

    
    
}
