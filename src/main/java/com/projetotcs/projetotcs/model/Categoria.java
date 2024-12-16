package com.projetotcs.projetotcs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "categorias") 
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do ID
    private int id;
    private String nome;
 
    
    public Categoria(String nome) {
        this.nome = nome;

    }

    
    public Categoria() {
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    
}
