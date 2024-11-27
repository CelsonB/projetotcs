package com.projetotcs.projetotcs.controller;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetotcs.projetotcs.model.ErrorResponse;
import com.projetotcs.projetotcs.model.Usuario;
import com.projetotcs.projetotcs.service.SessaoService;
import com.projetotcs.projetotcs.service.UsuarioService;



@RestController
@RequestMapping("/teste/projetotcs")


public class UsuarioController {
    

    @Autowired
    private UsuarioService usuarioServices;

    @Autowired
    private SessaoService sessaoService;




    //#region região de login

    @PostMapping("/login")
    public ResponseEntity<?> realizarLogin(@RequestBody Usuario user) {
        System.out.println("Login:"+user);
     

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse("Email inválido");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }

        if (usuarioServices.realizarLogin(user.getEmail(),user.getSenha())) {
            UUID sessionId = sessaoService.iniciarSessao(user);
            return ResponseEntity.ok(sessionId); 

        } else {
            System.out.println("entrou aqui, false");
            ErrorResponse errorResponse = new ErrorResponse("Credenciais inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse); 

        }
    }
    
    /*
     * cadastro de usuario
     */
    @PostMapping("/usuarios")

    public ResponseEntity cadastrarUsuario(@RequestBody Usuario user){
        boolean status = usuarioServices.cadastrarUsuario(user);
        if(status){
          return ResponseEntity.ok("");
        }else{
            ErrorResponse errorResponse = new ErrorResponse("Dados invalidos");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse); 
        }
            
        
    }

    @PostMapping ("/usuarios/logout")
    public void deslogar(){
        sessaoService.sairDeTodasSessoes();
    }
    //#endregion


    @GetMapping("/usuarios")
    public ResponseEntity<ArrayList<Usuario>> listarUsuarios(){

        return ResponseEntity.ok(usuarioServices.listarUsuarios());

    }

    @GetMapping("/usuarios/{email}")
    public ResponseEntity<?> listarPorEmail(@PathVariable String email){


        Optional<Usuario> user = usuarioServices.listarPorEmail(email);


        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            ErrorResponse errorResponse = new ErrorResponse("Usuario não encontrado");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }

    }

    @PutMapping("/usuarios/{email}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable String email,@RequestBody Usuario usuario){
        
        Usuario user = usuarioServices.atualizarUsuario(email, usuario);
        if(user==null){
            ErrorResponse errorResponse = new ErrorResponse("Email não encontrado");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }else{
            return ResponseEntity.ok(user);
        }
        

    }


    @DeleteMapping("/usuarios/{email}")
     public ResponseEntity deletarUsuario(@PathVariable String email){

        if(usuarioServices.deletarUsuario(email)){
            return ResponseEntity.ok("");
        }else{
            ErrorResponse errorResponse = new ErrorResponse("Usuario não encontrado");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse); 
        }

     }
    
}
