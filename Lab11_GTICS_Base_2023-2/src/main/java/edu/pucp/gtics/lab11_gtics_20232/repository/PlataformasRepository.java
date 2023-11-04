package edu.pucp.gtics.lab11_gtics_20232.repository;

import edu.pucp.gtics.lab11_gtics_20232.entity.Plataformas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PlataformasRepository extends JpaRepository<Plataformas,Integer> {
}
