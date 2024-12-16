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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetotcs.projetotcs.model.ErrorResponse;
import com.projetotcs.projetotcs.model.TokenResponse;
import com.projetotcs.projetotcs.model.Usuario;
import com.projetotcs.projetotcs.service.SessaoService;
import com.projetotcs.projetotcs.service.UsuarioService;
import java.util.regex.Pattern;




@RestController
@RequestMapping("")


public class UsuarioController {
    

    @Autowired
    private UsuarioService usuarioServices;

    private final SessaoService sessaoService;

    public UsuarioController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }



    //#region região de login

    @PostMapping("/login")
    public ResponseEntity<?> realizarLogin(@RequestBody Usuario user) {
        System.out.println("Login:"+user);
    

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse("Email inválido");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }

        if (usuarioServices.realizarLogin(user.getEmail(),user.getSenha())) {

            String sessionId = sessaoService.iniciarSessao(user);

            return ResponseEntity.ok(new TokenResponse(sessionId) ); 

        } else {
            System.out.println("entrou aqui, false");
            ErrorResponse errorResponse = new ErrorResponse("Email e/ou senha invalidos");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse); 

        }
    }
    
    /*
     * cadastro de usuario
     */
    @PostMapping("/usuarios")

    public ResponseEntity cadastrarUsuario(@RequestBody Usuario user){
        boolean status = usuarioServices.cadastrarUsuario(user);

        if (user == null || !isValidUser (user)) {
            // Retorna 400 Bad Request se os dados do usuário forem inválidos
            ErrorResponse errorResponse = new ErrorResponse("Dados inválidos");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        if(status){
          return ResponseEntity.ok("");
        }else{
            ErrorResponse errorResponse = new ErrorResponse("Email ja cadastrado");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse); 
        }
    }

    private boolean isValidUser (Usuario user) {
        if(user.getNome().length()>3 && user.getNome().length()<100){
            if(user.getEmail().contains("@") && isNumeric(user.getSenha())){
                return true;
            }
            return false;
        }
        else{
            return false;
        }
        
    }


    public static boolean isNumeric(String str) {
        return str != null && Pattern.matches("\\d+", str);
    }


@PostMapping("/logout")

    public ResponseEntity<String> deslogar(@RequestHeader(value = "Authorization", required = false) String authorizationHeader ) {
        
        
        if(isHeaderValid(authorizationHeader)){
            sessaoService.sairDeTodasSessoes();
            return ResponseEntity.ok("Deslogado com sucesso.");
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Cabeçalho de autorização inválido.");
        }
        
    }

    
    //#endregion
 

    @GetMapping("/usuarios")
    public ResponseEntity<?> listarUsuarios(
        @RequestHeader(value = "Authorization", required = false) String authorizationHeader ){

        if(isHeaderValid(authorizationHeader)){
            return ResponseEntity.ok(usuarioServices.listarUsuarios());
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Cabeçalho de autorização inválido.");
        }
       

    }

    @GetMapping("/usuarios/{email}")
    public ResponseEntity<?> listarPorEmail(@PathVariable String email, @RequestHeader(value = "Authorization", required = false) String authorizationHeader ){

        
        if(isHeaderValid(authorizationHeader)){
            Optional<Usuario> user = usuarioServices.listarPorEmail(email);

            if (user.isPresent()) {
                return ResponseEntity.ok(user.get());
            } else {
                ErrorResponse errorResponse = new ErrorResponse("Usuario não encontrado");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }

        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Cabeçalho de autorização inválido.");
        }


      


        

    }

    @PutMapping("/usuarios/{email}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable String email, @RequestBody Usuario usuario,@RequestHeader(value = "Authorization", required = false) String authorizationHeader ){



        if(isHeaderValid(authorizationHeader)){
            usuario.setEmail(email);
            if (usuario == null || !isValidUser (usuario)) {
                // Retorna 400 Bad Request se os dados do usuário forem inválidos
                ErrorResponse errorResponse = new ErrorResponse("Dados inválidos");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }
            Usuario user = usuarioServices.atualizarUsuario(email, usuario);
            if(user==null){
                ErrorResponse errorResponse = new ErrorResponse("Email não encontrado");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }else{
                return ResponseEntity.ok(user);
            }

        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Cabeçalho de autorização inválido.");
        }




        
        
 
    }


    @DeleteMapping("/usuarios/{email}")
     public ResponseEntity deletarUsuario(@PathVariable String email, 
     @RequestBody Usuario usuario,@RequestHeader(value = "Authorization", required = false) String authorizationHeader ){


        if(isHeaderValid(authorizationHeader)){

        if(!email.contains("@")){
                ErrorResponse errorResponse = new ErrorResponse("Dados inválidos");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
         }
 
         if(usuarioServices.deletarUsuario(email)){
             return ResponseEntity.ok("");
         }else{
             ErrorResponse errorResponse = new ErrorResponse("Usuario não encontrado");
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse); 
         }

        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Cabeçalho de autorização inválido.");
        }

     }




     public boolean isHeaderValid(String authorizationHeader ){
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return false;
        }
        
        String token = authorizationHeader.substring(7); 

        if (sessaoService.obterSessao(token)==null) {
            return false;
        }
        return true;
    }

    
}
