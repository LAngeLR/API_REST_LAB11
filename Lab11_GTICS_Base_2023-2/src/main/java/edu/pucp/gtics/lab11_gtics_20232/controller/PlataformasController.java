package edu.pucp.gtics.lab11_gtics_20232.controller;

import edu.pucp.gtics.lab11_gtics_20232.entity.Juegos;
import edu.pucp.gtics.lab11_gtics_20232.entity.Plataformas;
import edu.pucp.gtics.lab11_gtics_20232.repository.PlataformasRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/plataformas")

public class PlataformasController {

    final
    PlataformasRepository plataformasRepository;

    public PlataformasController(PlataformasRepository plataformasRepository) {
        this.plataformasRepository = plataformasRepository;
    }

    @GetMapping(value = {"/lista"})
    public List<Plataformas> listaPlataformas() {
        return plataformasRepository.findAll();
    }

    @PostMapping("/lista")
    public ResponseEntity<HashMap<String, Object>> guardarJuego(
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

}