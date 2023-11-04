package edu.pucp.gtics.lab11_gtics_20232.controller;

import edu.pucp.gtics.lab11_gtics_20232.entity.Paises;
import edu.pucp.gtics.lab11_gtics_20232.repository.PaisesRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/paises")
public class PaisesController {

    final
    PaisesRepository paisesRepository;

    public PaisesController(PaisesRepository paisesRepository) {
        this.paisesRepository = paisesRepository;
    }

    @GetMapping(value = {"/lista"})
    public List<Paises> listaPaises() {
        return paisesRepository.findAll();
    }
}
