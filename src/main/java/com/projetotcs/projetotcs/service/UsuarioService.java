package com.projetotcs.projetotcs.service;

import java.util.ArrayList;
import java.util.List;
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


    
    public void addAdminUser () {
        Usuario admin = new Usuario("admin@email.com","123456",true);
        this.cadastrarUsuario(admin);//aqui deve cadastrar o admin
    }

    //#region login e cadastro


    public boolean realizarLogin(String email, String senha){

        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        return usuario.isPresent() && usuario.get().getSenha().equals(senha);
       

    }

    public boolean cadastrarUsuario(Usuario user){

        if (!usuarioRepository.findByEmail(user.getEmail()).isPresent()) {
            usuarioRepository.save(user);
            return true;
        } else {
            return false;
        }

    }

    //#endregion



    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();

    }


    public Optional<Usuario> listarPorEmail(String email){

        return usuarioRepository.findByEmail(email);

    }

    public Usuario atualizarUsuario(String email, Usuario usuario){

        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(email);
        
        if (usuarioExistente.isPresent()) {
            Usuario usuarioAtualizado = usuarioExistente.get();
            usuarioAtualizado.setNome(usuario.getNome());
            usuarioAtualizado.setSenha(usuario.getSenha()); // Atualize outros campos conforme necessário
            return usuarioRepository.save(usuarioAtualizado); // Salva as alterações
        }
        return null; // Retorna null se o usuário não existir

    }

    public boolean deletarUsuario(String email){

        try {
            usuarioRepository.deleteById(email);
            return true; // Retorna true se a exclusão foi bem-sucedida
        } catch (Exception e) {
            return false; // Retorna false se ocorrer um erro (por exemplo, se o usuário não existir)
        }


    }
}
