package com.projetotcs.projetotcs.repository;

import com.projetotcs.projetotcs.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    // Você pode adicionar métodos personalizados aqui, se necessário
}