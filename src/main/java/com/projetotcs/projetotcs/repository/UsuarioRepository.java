package com.projetotcs.projetotcs.repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;


import com.projetotcs.projetotcs.model.Usuario;






@Repository
public class UsuarioRepository {
    

    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    Usuario admin2 = new Usuario("admin@email.com", "123456");
       
    public UsuarioRepository(){
        usuarios.add(admin2);
    }


    //#region região de login e cadastro

    public boolean realizarLogin(String email, String senha){
        
        for(Usuario user: usuarios){
            if(user.getEmail().equals(email) && user.getSenha().equals(senha)){
                System.out.println("repository, true");
                return true;

            }
        }
        System.out.println("repository, false");
        return false;
    }

    public boolean cadastrarUsuario(Usuario user){

        usuarios.add(user);
        return true; 
    }

    //#endregion
    
    
    public ArrayList<Usuario> listarUsuarios(){
        return usuarios;
    }

    public Optional<Usuario> listarPorEmail(String email){

        return usuarios.stream()
        .filter(usuario-> usuario.getEmail().equals(email))
        .findFirst();
    }


    public Usuario atualizarUsuario(String email, Usuario usuario){
        
      if(deletarUsuario(email)){
        
        usuario.setEmail(email);
        cadastrarUsuario(usuario);

        return usuario;
      }else{
        return null;
      }
        
     
        
    }


    public boolean deletarUsuario(String email){

        
        Optional <Usuario> usuario = listarPorEmail(email);
        if(usuario.isPresent()){
            usuarios.remove(usuario.get());
            return true;
        }else{
            return false;
        }

    }

}
