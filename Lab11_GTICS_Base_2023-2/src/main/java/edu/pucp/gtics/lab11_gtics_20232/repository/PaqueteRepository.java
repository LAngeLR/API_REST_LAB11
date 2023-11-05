package edu.pucp.gtics.lab11_gtics_20232.repository;

import edu.pucp.gtics.lab11_gtics_20232.entity.PaqueteJuegos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaqueteRepository extends JpaRepository<PaqueteJuegos, Integer> {

    @Query
    (value = "SELECT * FROM paquetejuegos WHERE idcarritocompras = ?1 AND idjuegoseleccionado = ?2", nativeQuery = true)
    PaqueteJuegos buscarPaquetePorCarritoYJuego(Integer idCarrito, Integer idJuego);

    @Query
    (value = "SELECT * FROM paquetejuegos WHERE idcarritocompras = ?1", nativeQuery = true)
    List<PaqueteJuegos> listarPaquetesPorUsuario(Integer idUsuario);
}
