package com.projetotcs.projetotcs.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.projetotcs.projetotcs.model.Categoria;

@Repository
public class CategoriaRepository {
    
    ArrayList<Categoria> catLista = new ArrayList<>();
    
    public boolean cadastrarCategoria(String nomeCategoria){

        if(catLista.add(new Categoria(nomeCategoria, catLista.size()+1))){
            return true; 

        }else{
            return false;
             
        }
        
    }

    public ArrayList<Categoria> listarCategorias (){
        return catLista; 
    }

    public Categoria pesquisarCategoriaPorId(int id ){


        Categoria catTemp = catLista.get(id);
        if(catTemp!=null){
            return catTemp;
        }
        return null;   
    }

    public boolean pesquisarCategoriaPorNome(String nomeCategoria){
        
        for(Categoria cat : catLista){
            if(cat.equals(nomeCategoria)){
                return true; 
            }
        }
        return false;
    }

    public boolean deletarCategoria(int id){
        
        if(catLista.removeIf(categorias -> categorias.getId() == id)){
            return true;
        }        
        return false;
   


    }

    




}
