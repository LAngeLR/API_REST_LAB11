package edu.pucp.gtics.lab11_gtics_20232.controller;

import edu.pucp.gtics.lab11_gtics_20232.entity.*;
import edu.pucp.gtics.lab11_gtics_20232.entity.Plataformas;
import edu.pucp.gtics.lab11_gtics_20232.repository.JuegosxUsuarioRepository;
import edu.pucp.gtics.lab11_gtics_20232.repository.PlataformasRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/plataformas")

public class PlataformasController {

    final PlataformasRepository plataformasRepository;
    final JuegosxUsuarioRepository juegosxUsuarioRepository;

    public PlataformasController(PlataformasRepository plataformasRepository, JuegosxUsuarioRepository juegosxUsuarioRepository) {
        this.plataformasRepository = plataformasRepository;
        this.juegosxUsuarioRepository = juegosxUsuarioRepository;
    }

    @GetMapping("/lista")
    public ResponseEntity<Object> obtenerPlataformaOLista(@RequestParam(name = "id", required = false) String idStr) {
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Optional<Plataformas> plataforma = plataformasRepository.findById(id);
                if (plataforma.isPresent()) {
                    return ResponseEntity.ok(plataforma.get());
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }
            } catch (NumberFormatException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } else {
            List<Plataformas> plataformas = plataformasRepository.findAll();
            return ResponseEntity.ok(plataformas);
        }
    }

    @PostMapping("/lista")
    public ResponseEntity<HashMap<String, Object>> guardarplataforma(
            @RequestBody Plataformas plataformas,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseJson = new HashMap<>();

        plataformasRepository.save(plataformas);
        if (fetchId) {
            responseJson.put("id", plataformas.getIdplataforma());
        }
        responseJson.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }

    @PutMapping(value = {"/lista"})
    public ResponseEntity<HashMap<String, Object>> actualizar(@RequestBody Plataformas plataformaRecibida) {

        HashMap<String, Object> rpta = new HashMap<>();
        System.out.println(plataformaRecibida.getIdplataforma());
        if (plataformaRecibida.getIdplataforma() != 0 && plataformaRecibida.getIdplataforma() > 0) {

            Optional<Plataformas> byId = plataformasRepository.findById(plataformaRecibida.getIdplataforma());


            if (byId.isPresent()) {
                Plataformas plataformaFromDb = byId.get();

                if (plataformaRecibida.getNombre() != null)
                    plataformaFromDb.setNombre(plataformaRecibida.getNombre());

                if (plataformaRecibida.getDescripcion() != null)
                    plataformaFromDb.setDescripcion(plataformaRecibida.getDescripcion());

                plataformasRepository.save(plataformaFromDb);
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