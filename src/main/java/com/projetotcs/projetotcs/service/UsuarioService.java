package com.projetotcs.projetotcs.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetotcs.projetotcs.model.Usuario;
import com.projetotcs.projetotcs.repository.UsuarioRepository;





@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //#region login e cadastro

    public boolean realizarLogin(String email, String senha){

       if(usuarioRepository.realizarLogin(email,senha)){
        System.out.println("service, true");
        return true;
       }else{
        System.out.println("service, false");
        return false;
       } 
       

    }

    public boolean cadastrarUsuario(Usuario user){

       return  usuarioRepository.cadastrarUsuario(user);

    }

    //#endregion



    public ArrayList<Usuario> listarUsuarios(){
        return usuarioRepository.listarUsuarios();

    }


    public Optional<Usuario> listarPorEmail(String email){

        return  usuarioRepository.listarPorEmail(email);

    }

    public Usuario atualizarUsuario(String email, Usuario usuario){
        return usuarioRepository.atualizarUsuario(email, usuario);

    }

    public boolean deletarUsuario(String email){

        return usuarioRepository.deletarUsuario(email);

    }
}
