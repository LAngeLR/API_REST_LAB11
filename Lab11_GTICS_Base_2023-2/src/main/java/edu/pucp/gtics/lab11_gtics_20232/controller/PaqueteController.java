package edu.pucp.gtics.lab11_gtics_20232.controller;

import edu.pucp.gtics.lab11_gtics_20232.entity.CarritoCompras;
import edu.pucp.gtics.lab11_gtics_20232.entity.PaqueteJuegos;
import edu.pucp.gtics.lab11_gtics_20232.repository.PaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/paquetes")
public class PaqueteController {

    @Autowired
    private PaqueteRepository paqueteRepository;

    @GetMapping("/buscarPaquete")
    public PaqueteJuegos buscarPaquete(@RequestParam(name = "idCarrito", required = false) Integer idCarrito,
                                       @RequestParam(name = "idJuego", required = false) Integer idJuego) {
        HashMap<String, Object> response = new HashMap<>();

        if(idCarrito != null && idCarrito > 0 && idJuego != null && idJuego > 0){
            PaqueteJuegos paqueteJuegos = paqueteRepository.buscarPaquetePorCarritoYJuego(idCarrito,idJuego);
            if(paqueteJuegos != null){
                response.put("result","ok");
                response.put("carrito",paqueteJuegos);
                ResponseEntity.ok(response);
                return paqueteJuegos;
            } else {
                response.put("result","error");
                response.put("msg","El carrito no existe");
                ResponseEntity.badRequest().body(response);
                return null;
            }
        } else {
            response.put("result","error");
            response.put("msg","El ID es requerido");
            ResponseEntity.badRequest().body(response);
            return null;

        }
    }

    @GetMapping(value = "/lista")
    public List<PaqueteJuegos> listarPaquetesPorUsuario(@RequestParam(name = "idUsuario", required = false) Integer idUsuario){
        return paqueteRepository.listarPaquetesPorUsuario(idUsuario);
    }

    @PostMapping(value = "/registro")
    public ResponseEntity<Object> registrarPaquete(@RequestBody PaqueteJuegos paqueteJuegos){

        HashMap<String, Object> response = new HashMap<>();

        paqueteRepository.save(paqueteJuegos);
        response.put("result","ok");
        response.put("msg","Paquete registrado correctamente");

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/registro")
    public ResponseEntity<Object> actualizarPaquete(@RequestBody PaqueteJuegos paqueteJuegos){

        HashMap<String, Object> response = new HashMap<>();

        paqueteRepository.save(paqueteJuegos);
        response.put("result","ok");
        response.put("msg","Paquete registrado correctamente");

        return ResponseEntity.ok(response);
    }

}
