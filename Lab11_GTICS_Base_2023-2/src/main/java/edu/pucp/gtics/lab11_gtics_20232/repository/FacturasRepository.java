package edu.pucp.gtics.lab11_gtics_20232.repository;

import edu.pucp.gtics.lab11_gtics_20232.entity.Facturas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FacturasRepository extends JpaRepository<Facturas, Integer> {

    @Query(value = "select * from factura where idjuegosxusuario = ?1",nativeQuery = true)
    List<Facturas> buscarPorIdJuegosxUsuario(int id);
}
