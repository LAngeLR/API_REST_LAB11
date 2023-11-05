package edu.pucp.gtics.lab11_gtics_20232.repository;

import edu.pucp.gtics.lab11_gtics_20232.entity.CarritoCompras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends JpaRepository<CarritoCompras, Integer> {

    @Query
    (value = "SELECT * FROM carritocompras WHERE usuarios_idusuario = ?1", nativeQuery = true)
    CarritoCompras buscarCarritoPorUsuario(Integer idUsuario);
}
