package com.projetotcs.projetotcs.model;







import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "usuarios")

public class Usuario {
    


    @Id
    @Column(unique = true, nullable = false) // Ensure email is unique and not null
    private String email;

    private String nome; 
    private String senha;
   
    private Boolean admin = false;
    

    public Usuario(String senha, String email) {
        this.senha = senha;
        this.email = email;
    } 

    public Usuario(String senha, String email,boolean role) {
        this.senha = senha;
        this.email = email;
        this.admin = role;
    } 



    //#region set e getters 
    
    
    public Boolean isAdmin(){
        return this.admin;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "Usuario [nome=" + nome + ", senha=" + senha + ", email=" + email + "]";
    }

    public Usuario() {
    } 

    //#endregion
    
    
}
