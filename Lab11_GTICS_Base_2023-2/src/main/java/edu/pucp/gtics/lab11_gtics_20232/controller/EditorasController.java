package edu.pucp.gtics.lab11_gtics_20232.controller;

import edu.pucp.gtics.lab11_gtics_20232.entity.Editoras;
import edu.pucp.gtics.lab11_gtics_20232.entity.Generos;
import edu.pucp.gtics.lab11_gtics_20232.repository.EditorasRepository;
import edu.pucp.gtics.lab11_gtics_20232.repository.GenerosRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("editoras")
public class EditorasController {

    final
    EditorasRepository editorasRepository;

    public EditorasController(EditorasRepository editorasRepository) {
        this.editorasRepository = editorasRepository;
    }

    @GetMapping(value = {"/lista"})
    public List<Editoras> listaEditoras() {
        return editorasRepository.findAll();
    }

}
