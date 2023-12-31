package edu.pucp.gtics.lab11_gtics_20232.controller;

import edu.pucp.gtics.lab11_gtics_20232.entity.User;
import edu.pucp.gtics.lab11_gtics_20232.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/listaUsuarios")
    public List<User> listarUsuarios(){
        return userRepository.findAll();
    }

    @PostMapping(value = "/registro")
    public ResponseEntity<HashMap<String, Object>> registrarUsuario(@RequestBody User usuario){

            HashMap<String, Object> response = new HashMap<>();

            userRepository.save(usuario);
            response.put("result","ok");
            response.put("msg","Usuario registrado correctamente");

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PutMapping(value = "/registro")
    public ResponseEntity<HashMap<String, Object>> actualizarUsuario(@RequestBody User usuario){

        HashMap<String, Object> response = new HashMap<>();

        userRepository.save(usuario);
        response.put("result","ok");
        response.put("msg","Usuario registrado correctamente");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PutMapping("/actualizarUsuario")
    public ResponseEntity<HashMap<String, Object>> cambiarRol(@RequestBody User usuario) {

        HashMap<String, Object> response = new HashMap<>();

        if(usuario.getIdusuario() != null && usuario.getIdusuario() > 0){
            Optional<User> usuarioOptional = userRepository.findById(usuario.getIdusuario());
            if(usuarioOptional.isPresent()){
                User usuarioActual = getUser(usuario, usuarioOptional);
                userRepository.save(usuarioActual);
                response.put("result","Usuario actualizado correctamente");
                return ResponseEntity.ok(response);
            } else {
                response.put("result","error");
                response.put("msg","El usuario no existe");
                return ResponseEntity.badRequest().body(response);
            }
        } else {
            response.put("result","error");
            response.put("msg","El ID es requerido");
            return ResponseEntity.badRequest().body(response);
        }

    }

    @GetMapping(value = "/buscarUsuario")
    public User buscarUsuario(@RequestParam("id") Integer idUsuario){
        HashMap<String, Object> response = new HashMap<>();

        if(idUsuario != null && idUsuario > 0){
            Optional<User> usuarioOptional = userRepository.findById(idUsuario);
            if(usuarioOptional.isPresent()){
                response.put("result","ok");
                response.put("usuario",usuarioOptional.get());
                ResponseEntity.ok(response);
                return usuarioOptional.get();
            } else {
                response.put("result","error");
                response.put("msg","El usuario no existe");
                ResponseEntity.badRequest().body(response);
                return null;
            }
        } else {
            response.put("result","error");
            response.put("msg","El ID es requerido");
            ResponseEntity.badRequest().body(response);
            return null;

        }
    }

    @GetMapping(value = "/buscarUsuarioPorCorreo")
    public User buscarUsuarioPorCorreo(@RequestParam("correo") String correo){
        HashMap<String, Object> response = new HashMap<>();

        if(correo != null){
            User usuario = userRepository.findByCorreo(correo);
            if(usuario != null){
                response.put("result","ok");
                response.put("usuario",usuario);
                ResponseEntity.ok(response);
                return usuario;
            } else {
                response.put("result","error");
                response.put("msg","El usuario no existe");
                ResponseEntity.badRequest().body(response);
                return null;
            }
        } else {
            response.put("result","error");
            response.put("msg","El ID es requerido");
            ResponseEntity.badRequest().body(response);
            return null;

        }
    }

    private static User getUser(User usuario, Optional<User> usuarioOptional) {
        User usuarioActual = usuarioOptional.get();

        if(usuario.getNombres() != null ){
            usuarioActual.setNombres(usuario.getNombres());
        }

        if(usuario.getApellidos() != null ){
            usuarioActual.setApellidos(usuario.getApellidos());
        }

        if(usuario.getAutorizacion() != null ){
            usuarioActual.setAutorizacion(usuario.getAutorizacion());
        }

        if(usuario.getCorreo() != null ){
            usuarioActual.setCorreo(usuario.getCorreo());
        }

        if(usuario.getPassword() != null ){
            usuarioActual.setPassword(usuario.getPassword());
        }

        if(usuario.getEnabled() != 0 ){
            usuarioActual.setEnable(usuario.getEnabled());
        }
        return usuarioActual;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String, String>> gestionExcepcion(HttpServletRequest request){
        HashMap<String, String> response = new HashMap<>();

        if(request.getMethod().equals("POST") || request.getMethod().equals("PUT")){
            response.put("result","error");
            response.put("msg","Error en el formato JSON");
        }

        return ResponseEntity.badRequest().body(response);
    }

}