package com.projetotcs.projetotcs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import com.projetotcs.projetotcs.model.Categoria;
import com.projetotcs.projetotcs.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository; 

    public Categoria cadastrarCategoria(String nomeCategoria) {
        Categoria novaCategoria = new Categoria(nomeCategoria);
        return categoriaRepository.save(novaCategoria); // Salva a nova categoria no banco de dados
    }

    public List<Categoria> listarCategorias (){
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> pesquisarCategoriaPorId(int id) {
        return categoriaRepository.findById(id); // Retorna a categoria com o ID especificado
    }

    public boolean pesquisarCategoriaPorNome(String nomeCategoria) {
        return categoriaRepository.findAll().stream().anyMatch(cat -> cat.getNome().equals(nomeCategoria)); // Verifica se existe uma categoria com o nome especificado
    }

    public boolean deletarCategoria(int id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id); // Deleta a categoria com o ID especificado
            return true;
        }
        return false;
    }
        
     
   




}
