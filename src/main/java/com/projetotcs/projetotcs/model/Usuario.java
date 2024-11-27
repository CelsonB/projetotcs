package com.projetotcs.projetotcs.model;

public class Usuario {
    
    private String nome; 
    private String senha;
    private String email;
    
    
    //#region set e getters 
    
    


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
    public Usuario(String senha, String email) {
        this.senha = senha;
        this.email = email;
    }
    public Usuario() {
    } 

    //#endregion
    
    
}
