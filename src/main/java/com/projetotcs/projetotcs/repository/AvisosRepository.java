package com.projetotcs.projetotcs.repository;



import com.projetotcs.projetotcs.model.Aviso;
import com.projetotcs.projetotcs.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisosRepository extends JpaRepository<Aviso, Long> {
            
 
}
