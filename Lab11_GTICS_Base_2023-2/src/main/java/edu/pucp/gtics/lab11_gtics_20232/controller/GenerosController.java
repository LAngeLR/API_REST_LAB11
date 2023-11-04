package edu.pucp.gtics.lab11_gtics_20232.controller;

import edu.pucp.gtics.lab11_gtics_20232.entity.Generos;
import edu.pucp.gtics.lab11_gtics_20232.repository.GenerosRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/generos")
public class GenerosController {

    final
    GenerosRepository generosRepository;

    public GenerosController(GenerosRepository generosRepository) {
        this.generosRepository = generosRepository;
    }

    @GetMapping(value = {"/lista"})
    public List<Generos> listaGeneros() {
        return generosRepository.findAll();
    }

}
