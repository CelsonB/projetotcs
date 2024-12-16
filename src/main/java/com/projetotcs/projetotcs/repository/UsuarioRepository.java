package com.projetotcs.projetotcs.repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;


import com.projetotcs.projetotcs.model.Usuario;



@Repository
public class UsuarioRepository {
    

    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    Usuario admin2 = new Usuario("123456", "admin@email.com", true);
       
    public UsuarioRepository(){
        admin2.setNome("admin");
        usuarios.add(admin2);
    }
    


    //#region região de login e cadastro

    public boolean realizarLogin(String email, String senha){
        
        for(Usuario user: usuarios){
            if(user.getEmail().equals(email) && user.getSenha().equals(senha)){
                
                return true;
            }
        }
        System.out.println("repository, false");
        return false;
    }

    public boolean cadastrarUsuario(Usuario user){

        //usuarios.add(user);
        System.out.println(user);
        if(!isEmailUsed(user.getEmail())){
            usuarios.add(user);
            return true; 
        }else{
            return false;
        }
      
    }

    //#endregion

     
    private boolean isEmailUsed(String email){
        Optional <Usuario> usuario = listarPorEmail(email);

        if(usuario.isPresent()){
            return true;
        }else{
            return false;
        }
   
    }
    
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
