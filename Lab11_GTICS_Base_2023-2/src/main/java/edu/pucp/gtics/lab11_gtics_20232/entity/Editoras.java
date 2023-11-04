package edu.pucp.gtics.lab11_gtics_20232.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "editoras")
@Getter
@Setter
public class Editoras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ideditora", nullable = false)
    private int idEditora;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "descripcion", length = 200)
    private String descripcion;
}
