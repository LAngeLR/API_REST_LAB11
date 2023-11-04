package edu.pucp.gtics.lab11_gtics_20232.controller;

import edu.pucp.gtics.lab11_gtics_20232.entity.Distribuidoras;
import edu.pucp.gtics.lab11_gtics_20232.entity.Juegos;
import edu.pucp.gtics.lab11_gtics_20232.repository.DistribuidorasRepository;
import edu.pucp.gtics.lab11_gtics_20232.repository.JuegosRepository;
import edu.pucp.gtics.lab11_gtics_20232.repository.PaisesRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/distribuidora")

public class DistribuidorasController {

    final
    JuegosRepository juegosRepository;
    final
    DistribuidorasRepository distribuidorasRepository;
    final
    PaisesRepository paisesRepository;

    public DistribuidorasController(DistribuidorasRepository distribuidorasRepository, PaisesRepository paisesRepository, JuegosRepository juegosRepository) {
        this.distribuidorasRepository = distribuidorasRepository;
        this.paisesRepository = paisesRepository;
        this.juegosRepository = juegosRepository;
    }

    @GetMapping("/lista")
    public ResponseEntity<HashMap<String, Object>> obtenerDistribuidoraoOLista(@RequestParam(name = "id", required = false) Integer id) {
        HashMap<String, Object> respuesta = new HashMap<>();
        if (id != null) {
            Optional<Distribuidoras> distribuidoras = distribuidorasRepository.findById(id);
            if (distribuidoras.isPresent()) {
                respuesta.put("result", "ok");
                respuesta.put("distribuidora", distribuidoras.get());
            } else {
                respuesta.put("result", "no existe");
            }
        } else {
            List<Distribuidoras> distribuidoras = distribuidorasRepository.findAll();
            respuesta.put("result", "ok");
            respuesta.put("distribuidora", distribuidoras);
        }
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/lista")
    public ResponseEntity<HashMap<String, Object>> borrar(@RequestParam("id") String idStr) {

        try {
            int id = Integer.parseInt(idStr);

            HashMap<String, Object> rpta = new HashMap<>();

            Optional<Distribuidoras> byId = distribuidorasRepository.findById(id);
            if (byId.isPresent()) {
                Distribuidoras distribuidora = byId.get();

                List<Juegos> juegosWithDistribuidora = juegosRepository.buscar(distribuidora.getIddistribuidora());
                for (Juegos juego : juegosWithDistribuidora) {
                    juego.setDistribuidora(null);
                    juegosRepository.save(juego);
                }
                distribuidorasRepository.deleteById(id);
                rpta.put("result", "ok");
            } else {
                rpta.put("result", "no ok");
                rpta.put("msg", "el ID enviado no existe");
            }
            return ResponseEntity.ok(rpta);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/lista")
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

    @PutMapping(value = {"/lista"})
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
}

