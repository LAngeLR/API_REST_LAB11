package edu.pucp.gtics.lab11_gtics_20232.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

@Getter
@Setter

@Entity
@Table(name = "juegosxusuario")
public class JuegosxUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjuegosxusuario", nullable = false)
    private int idJuegosxUsuario;

    @ManyToOne
    @JoinColumn(name = "idjuego")
    @Valid
    private Juegos juegos;

    @ManyToOne
    @JoinColumn(name = "idusuario")
    @Valid
    private User user;

    @Column(name = "cantidad")
    private int cantidad;

    /*Falta entity factura. Ver si lo usaremos luego*/

}
