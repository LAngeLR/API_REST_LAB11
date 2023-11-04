package edu.pucp.gtics.lab11_gtics_20232.repository;


import edu.pucp.gtics.lab11_gtics_20232.entity.Juegos;
import edu.pucp.gtics.lab11_gtics_20232.entity.JuegosxUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public  interface JuegosxUsuarioRepository extends JpaRepository<JuegosxUsuario, Integer> {

    @Query(value = "select * from juegosxusuario where idjuego=?1",nativeQuery = true)
    List<JuegosxUsuario> buscar(int id);

}
