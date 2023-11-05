package edu.pucp.gtics.lab11_gtics_20232.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "carritocompras")
@Getter
@Setter
public class CarritoCompras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcarritocompras", nullable = false)
    private int idcarritocompras;

    @Column(name = "cantidadtotal")
    private double cantidadTotal;

    @OneToOne
    @JoinColumn(name = "usuarios_idusuario")
    private User usuario;


}
