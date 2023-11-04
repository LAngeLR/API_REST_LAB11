package edu.pucp.gtics.lab11_gtics_20232.controller;

import edu.pucp.gtics.lab11_gtics_20232.entity.*;
import edu.pucp.gtics.lab11_gtics_20232.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/juegos")
public class JuegosController {
    final
    JuegosRepository juegosRepository;
    final
    PlataformasRepository plataformasRepository;
    final
    DistribuidorasRepository distribuidorasRepository;
    final
    GenerosRepository generosRepository;
    final
    UserRepository userRepository;

    public JuegosController(JuegosRepository juegosRepository, PlataformasRepository plataformasRepository, DistribuidorasRepository distribuidorasRepository, GenerosRepository generosRepository, UserRepository userRepository) {
        this.juegosRepository = juegosRepository;
        this.plataformasRepository = plataformasRepository;
        this.distribuidorasRepository = distribuidorasRepository;
        this.generosRepository = generosRepository;
        this.userRepository = userRepository;
    }

    /*listar (localhost:8080/gameshop4/juegos/lista)*/
    @GetMapping(value = {"/lista"})
    public List<Juegos> listaJuegos (){
        return juegosRepository.findAll();
    }

    /*borrar (localhost:8080/gameshop4/juegos/borrar?id=1)*/
    @DeleteMapping("/borrar")
    public ResponseEntity<HashMap<String, Object>> borrar(@RequestParam("id") String idStr){

        try{
            int id = Integer.parseInt(idStr);

            HashMap<String, Object> rpta = new HashMap<>();

            Optional<Juegos> byId = juegosRepository.findById(id);
            if(byId.isPresent()){
                juegosRepository.deleteById(id);
                rpta.put("result","ok");
            }else{
                rpta.put("result","no ok");
                rpta.put("msg","el ID enviado no existe");
            }
            return ResponseEntity.ok(rpta);
        }catch (NumberFormatException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    /*crear (localhost:8080/gameshop4/juegos/crear) usar los nombres de la bd*/
    @PostMapping("/crear")
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

    /*obtener un juego (localhost:8080/gameshop4/juegos/obtener?id=5)*/
    @GetMapping(value = "/obtener")
    public ResponseEntity<HashMap<String, Object>> buscarProducto(@RequestParam("id") String idStr) {


        try {
            int id = Integer.parseInt(idStr);
            Optional<Juegos> byId = juegosRepository.findById(id);

            HashMap<String, Object> respuesta = new HashMap<>();

            if (byId.isPresent()) {
                respuesta.put("result", "ok");
                respuesta.put("producto", byId.get());
            } else {
                respuesta.put("result", "no existe");
            }
            return ResponseEntity.ok(respuesta);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /*actualizar un juego (localhost:8080/gameshop4/juegos/actualizar) esta para body-raw*/
    @PutMapping(value = { "/actualizar"}, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<HashMap<String, Object>> actualizar(Juegos juegoRecibido) {

        HashMap<String, Object> rpta = new HashMap<>();

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
                rpta.put("result", "error");
                rpta.put("msg", "El ID del juego enviado no existe");
                return ResponseEntity.badRequest().body(rpta);
            }
        } else {
            rpta.put("result", "error");
            rpta.put("msg", "debe enviar un juego con ID");
            return ResponseEntity.badRequest().body(rpta);
        }
    }


    /*
    @GetMapping("/juegos/editar")
    public String editarJuegos(@RequestParam("id") int id, Model model){
        Optional<Juegos> opt = juegosRepository.findById(id);
        List<Plataformas> listaPlataformas = plataformasRepository.findAll();
        List<Distribuidoras> listaDistribuidoras = distribuidorasRepository.findAll();
        List<Generos> listaGeneros = generosRepository.findAll();
        if (opt.isPresent()){
            Juegos juego = opt.get();
            model.addAttribute("juego", juego);
            model.addAttribute("listaPlataformas", listaPlataformas);
            model.addAttribute("listaDistribuidoras", listaDistribuidoras);
            model.addAttribute("listaGeneros", listaGeneros);
            return "juegos/editarFrm";
        }else {
            return "redirect:/juegos/lista";
        }
    }

    @PostMapping("/juegos/guardar")
    public String guardarJuegos(Model model, RedirectAttributes attr, @ModelAttribute("juego") @Valid Juegos juego, BindingResult bindingResult ){
        if(bindingResult.hasErrors( )){
            List<Plataformas> listaPlataformas = plataformasRepository.findAll();
            List<Distribuidoras> listaDistribuidoras = distribuidorasRepository.findAll();
            List<Generos> listaGeneros = generosRepository.findAll();
            model.addAttribute("juego", juego);
            model.addAttribute("listaPlataformas", listaPlataformas);
            model.addAttribute("listaDistribuidoras", listaDistribuidoras);
            model.addAttribute("listaGeneros", listaGeneros);
            return "juegos/editarFrm";
        } else {
            if (juego.getIdjuego() == 0) {
                attr.addFlashAttribute("msg", "Juego creado exitosamente");
            } else {
                attr.addFlashAttribute("msg", "Juego actualizado exitosamente");
            }
            juegosRepository.save(juego);
            return "redirect:/juegos/lista";
        }


    }

    @GetMapping("/juegos/borrar")
    public String borrarDistribuidora(@RequestParam("id") int id){
        Optional<Juegos> opt = juegosRepository.findById(id);
        if (opt.isPresent()) {
            juegosRepository.deleteById(id);
        }
        return "redirect:/juegos/lista";
    }
    */
}
