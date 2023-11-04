package edu.pucp.gtics.lab11_gtics_20232.controller;

import edu.pucp.gtics.lab11_gtics_20232.entity.Plataformas;
import edu.pucp.gtics.lab11_gtics_20232.repository.PlataformasRepository;
import org.springframework.web.bind.annotation.*;

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

}