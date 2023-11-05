package edu.pucp.gtics.lab11_gtics_20232.controller;

import edu.pucp.gtics.lab11_gtics_20232.entity.*;
import edu.pucp.gtics.lab11_gtics_20232.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/juegos")
public class JuegosController {
    final
    JuegosRepository juegosRepository;
    final PlataformasRepository plataformasRepository;
    final
    DistribuidorasRepository distribuidorasRepository;
    final
    GenerosRepository generosRepository;
    final
    UserRepository userRepository;
    private final JuegosxUsuarioRepository juegosxUsuarioRepository;

    public JuegosController(JuegosRepository juegosRepository, PlataformasRepository plataformasRepository, DistribuidorasRepository distribuidorasRepository, GenerosRepository generosRepository, UserRepository userRepository,
                            JuegosxUsuarioRepository juegosxUsuarioRepository) {
        this.juegosRepository = juegosRepository;
        this.plataformasRepository = plataformasRepository;
        this.distribuidorasRepository = distribuidorasRepository;
        this.generosRepository = generosRepository;
        this.userRepository = userRepository;
        this.juegosxUsuarioRepository = juegosxUsuarioRepository;
    }

    @GetMapping("/lista")
    public ResponseEntity<Object> obtenerJuegoOLista(@RequestParam(name = "id", required = false) String idStr) {
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Optional<Juegos> juego = juegosRepository.findById(id);
                if (juego.isPresent()) {
                    return ResponseEntity.ok(juego.get());
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }
            } catch (NumberFormatException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } else {
            List<Juegos> juegos = juegosRepository.findAll();
            return ResponseEntity.ok(juegos);
        }
    }


    @GetMapping("/listaMisJuegos")
    public List<JuegosxUsuario> obtenerMisJuegos(@RequestParam(name = "id") Integer idUsuario) {
/*        HashMap<String, Object> respuesta = new HashMap<>();

        try{*/
            Optional<User> usuario = userRepository.findById(idUsuario);
            if (usuario.isPresent()) {
                return juegosxUsuarioRepository.buscarJuegosPorUsuario(idUsuario);
            } else {
                return null;
            } /*else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
        catch(NumberFormatException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.ok(respuesta);*/
    }



    @PostMapping("/lista")
    public ResponseEntity<HashMap<String, Object>> guardarJuego(
            @RequestBody Juegos juegos,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseJson = new HashMap<>();

        juegosRepository.save(juegos);
        if (fetchId) {
            responseJson.put("id", juegos.getIdjuego());
        }
        responseJson.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }

    @PutMapping(value = { "/lista"})
    public ResponseEntity<HashMap<String, Object>> actualizar(@RequestBody Juegos juegoRecibido) {

        HashMap<String, Object> rpta = new HashMap<>();
        System.out.println(juegoRecibido.getIdjuego());
        if (juegoRecibido.getIdjuego() != 0 && juegoRecibido.getIdjuego() > 0) {

            Optional<Juegos> byId = juegosRepository.findById(juegoRecibido.getIdjuego());


            if (byId.isPresent()) {
                Juegos juegoFromDb = byId.get();

                if (juegoRecibido.getNombre() != null)
                    juegoFromDb.setNombre(juegoRecibido.getNombre());

                if (juegoRecibido.getDescripcion() != null)
                    juegoFromDb.setDescripcion(juegoRecibido.getDescripcion());

                if (juegoRecibido.getPrecio() != null) {
                    juegoFromDb.setPrecio(juegoRecibido.getPrecio());
                }

                if (juegoRecibido.getImage() != null)
                    juegoFromDb.setImage(juegoRecibido.getImage());

                if (juegoRecibido.getGenero() != null)
                    juegoFromDb.setGenero(juegoRecibido.getGenero());

                if (juegoRecibido.getPlataforma() != null)
                    juegoFromDb.setPlataforma(juegoRecibido.getPlataforma());

                if (juegoRecibido.getEditoras() != null)
                    juegoFromDb.setEditoras(juegoRecibido.getEditoras());

                if (juegoRecibido.getDistribuidora() != null)
                    juegoFromDb.setDistribuidora(juegoRecibido.getDistribuidora());

                juegosRepository.save(juegoFromDb);
                rpta.put("result", "ok");
                return ResponseEntity.ok(rpta);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
