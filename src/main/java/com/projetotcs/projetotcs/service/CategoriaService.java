package com.projetotcs.projetotcs.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import com.projetotcs.projetotcs.model.Categoria;
import com.projetotcs.projetotcs.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository; 

    public boolean cadastrarCategoria(String nomeCategoria){

        return categoriaRepository.cadastrarCategoria(nomeCategoria);
        
    }

    public ArrayList<Categoria> listarCategorias (){
       
        
        return categoriaRepository.listarCategorias();
    }

    public Categoria pesquisarCategoriaPorId(int id ){


        return categoriaRepository.pesquisarCategoriaPorId(id);
    
    }

    public boolean pesquisarCategoriaPorNome(String nomeCategoria){

        return categoriaRepository.pesquisarCategoriaPorNome( nomeCategoria);
    
        
      
    }

    public boolean deletarCategoria(int id){

        return deletarCategoria(id);

    }
        
     
   




}
