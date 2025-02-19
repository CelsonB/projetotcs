package com.projetotcs.projetotcs.repository;



import com.projetotcs.projetotcs.model.Aviso;
import com.projetotcs.projetotcs.model.Categoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisosRepository extends JpaRepository<Aviso, Integer> {
            
    List<Aviso> findByIdCategoria(int idCategoria);
    
}
