package edu.pucp.gtics.lab11_gtics_20232.controller;

import edu.pucp.gtics.lab11_gtics_20232.entity.CarritoCompras;
import edu.pucp.gtics.lab11_gtics_20232.entity.User;
import edu.pucp.gtics.lab11_gtics_20232.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping ("/carrito")
public class CarritoController {

    @Autowired
    private CarritoRepository carritoRepository;

    @GetMapping(value = "/buscarCarrito")
    public CarritoCompras buscarCarrito(@RequestParam(name = "id", required = false) Integer idUsuario) {
        HashMap<String, Object> response = new HashMap<>();

        if(idUsuario != null && idUsuario > 0){
            CarritoCompras carritoCompras = carritoRepository.buscarCarritoPorUsuario(idUsuario);
            if(carritoCompras != null){
                response.put("result","ok");
                response.put("carrito",carritoCompras);
                ResponseEntity.ok(response);
                return carritoCompras;
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

    @PostMapping(value = "/registro")
    public ResponseEntity<Object> registrarCarrito(@RequestBody CarritoCompras carritoCompras){

        HashMap<String, Object> response = new HashMap<>();

        carritoRepository.save(carritoCompras);
        response.put("result","ok");
        response.put("msg","Carrito registrado correctamente");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/registro")
    public ResponseEntity<Object> actualizarCarrito(@RequestBody CarritoCompras carritoCompras){

        HashMap<String, Object> response = new HashMap<>();

        carritoRepository.save(carritoCompras);
        response.put("result","ok");
        response.put("msg","Carrito registrado correctamente");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }




}
