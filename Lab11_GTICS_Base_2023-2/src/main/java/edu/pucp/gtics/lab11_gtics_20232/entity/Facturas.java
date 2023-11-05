package edu.pucp.gtics.lab11_gtics_20232.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Table(name = "factura")
@Getter
@Setter
public class Facturas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFactura", nullable = false)
    private int idFactura;

    @Column(name = "fechaEnvio", length = 50)
    private String fechaEnvio;

    @Column(name = "tarjeta", length = 200)
    private String tarjeta;

    @Column(name = "codigoVerificacion", length = 50)
    private String codigoVerificacion;

    @Column(name = "direccion", length = 200)
    private String direccion;

    @Column(name = "monto")
    private Double monto;

    @ManyToOne
    @JoinColumn(name = "idjuegosxusuario")
    @Valid
    private JuegosxUsuario idjuegoxusuario;

}
