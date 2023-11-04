package edu.pucp.gtics.lab11_gtics_20232.controller;

import edu.pucp.gtics.lab11_gtics_20232.entity.Distribuidoras;
import edu.pucp.gtics.lab11_gtics_20232.entity.Juegos;
import edu.pucp.gtics.lab11_gtics_20232.entity.Paises;
import edu.pucp.gtics.lab11_gtics_20232.repository.DistribuidorasRepository;
import edu.pucp.gtics.lab11_gtics_20232.repository.PaisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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

@Controller
@RequestMapping("/distribuidora")

public class DistribuidorasController {

    final
    DistribuidorasRepository distribuidorasRepository;
    final
    PaisesRepository paisesRepository;

    public DistribuidorasController(DistribuidorasRepository distribuidorasRepository, PaisesRepository paisesRepository) {
        this.distribuidorasRepository = distribuidorasRepository;
        this.paisesRepository = paisesRepository;
    }

    @GetMapping(value = {"/lista"})
    public List<Distribuidoras> listaDistribuidoras (){
        return distribuidorasRepository.findAll();
    }

    @DeleteMapping("/borrar")
    public ResponseEntity<HashMap<String, Object>> borrar(@RequestParam("id") String idStr){

        try{
            int id = Integer.parseInt(idStr);

            HashMap<String, Object> rpta = new HashMap<>();

            Optional<Distribuidoras> byId = distribuidorasRepository.findById(id);
            if(byId.isPresent()){
                distribuidorasRepository.deleteById(id);
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

    /*crear (localhost:8080/gameshop4/distribuidora/crear) usar los nombres de la bd*/
    @PostMapping("/crear")
    public ResponseEntity<HashMap<String, Object>> guardarDistribuidora(
            @RequestBody Distribuidoras distribuidoras,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseJson = new HashMap<>();

        distribuidorasRepository.save(distribuidoras);
        if (fetchId) {
            responseJson.put("id", distribuidoras.getIddistribuidora());
        }
        responseJson.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }

    /*obtener un juego (localhost:8080/gameshop4/distribuidora/obtener?id=5)*/
    @GetMapping(value = "/obtener")
    public ResponseEntity<HashMap<String, Object>> buscarDistribuidora(@RequestParam("id") String idStr) {


        try {
            int id = Integer.parseInt(idStr);
            Optional<Distribuidoras> byId = distribuidorasRepository.findById(id);

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

    /*actualizar un juego (localhost:8080/gameshop4/distribuidora/actualizar) esta para body-raw*/
    @PutMapping(value = { "/actualizar"})
    public ResponseEntity<HashMap<String, Object>> actualizar(@RequestBody Distribuidoras distribuidoraRecibida) {

        HashMap<String, Object> rpta = new HashMap<>();

        if (distribuidoraRecibida.getIddistribuidora() != 0 && distribuidoraRecibida.getIddistribuidora() > 0) {

            Optional<Distribuidoras> byId = distribuidorasRepository.findById(distribuidoraRecibida.getIddistribuidora());


            if (byId.isPresent()) {
                Distribuidoras distribuidoraFromDb = byId.get();

                if (distribuidoraRecibida.getNombre() != null)
                    distribuidoraFromDb.setNombre(distribuidoraRecibida.getNombre());

                if (distribuidoraRecibida.getDescripcion() != null)
                    distribuidoraFromDb.setDescripcion(distribuidoraRecibida.getDescripcion());

                if (distribuidoraRecibida.getWeb() != null) {
                    distribuidoraFromDb.setWeb(distribuidoraRecibida.getWeb());
                }

                if (distribuidoraRecibida.getFundacion() != null)
                    distribuidoraFromDb.setFundacion(distribuidoraRecibida.getFundacion());

                if (distribuidoraRecibida.getPais() != null)
                    distribuidoraFromDb.setPais(distribuidoraRecibida.getPais());

                distribuidorasRepository.save(distribuidoraFromDb);
                rpta.put("result", "ok");
                return ResponseEntity.ok(rpta);
            } else {
                rpta.put("result", "error");
                rpta.put("msg", "El ID de la distribuidora enviado no existe");
                return ResponseEntity.badRequest().body(rpta);
            }
        } else {
            rpta.put("result", "error");
            rpta.put("msg", "debe enviar una distribuidora con ID");
            return ResponseEntity.badRequest().body(rpta);
        }
    }

/*
    @GetMapping("/editar")
    public String editarDistribuidoras(@RequestParam("id") int id, Model model){
        Optional<Distribuidoras> opt = distribuidorasRepository.findById(id);
        List<Paises> listaPaises = paisesRepository.findAll();
        if (opt.isPresent()){
            Distribuidoras distribuidora = opt.get();
            model.addAttribute("distribuidora", distribuidora);
            model.addAttribute("listaPaises", listaPaises);
            return "distribuidoras/editarFrm";
        }else {
            return "redirect:/distribuidoras/lista";
        }

    }

    @GetMapping("/nuevo")
    public String nuevaDistribuidora(Model model, @ModelAttribute("distribuidora") Distribuidoras distribuidora){
        List<Paises> listaPaises = paisesRepository.findAll();
        model.addAttribute("listaPaises", listaPaises);
        return "distribuidoras/editarFrm";
    }

    @PostMapping("/guardar")
    public String guardarDistribuidora(Model model, RedirectAttributes attr, @ModelAttribute("distribuidora") @Valid Distribuidoras distribuidora , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<Paises> listaPaises = paisesRepository.findAll();
            model.addAttribute("distribuidora", distribuidora);
            model.addAttribute("listaPaises", listaPaises);
            return "distribuidoras/editarFrm";
        } else {
            if (distribuidora.getIddistribuidora() == 0) {
                attr.addFlashAttribute("msg", "Distribuidora creada exitosamente");
            } else {
                attr.addFlashAttribute("msg", "Distribuidora actualizada exitosamente");
            }
            distribuidorasRepository.save(distribuidora);
            return "redirect:/distribuidoras/lista";
        }
    }

    @GetMapping("/borrar")
    public String borrarDistribuidora(@RequestParam("id") int id){
        Optional<Distribuidoras> opt = distribuidorasRepository.findById(id);
        if (opt.isPresent()) {
            distribuidorasRepository.deleteById(id);
        }
        return "redirect:/distribuidoras/lista";
    }
*/
}
