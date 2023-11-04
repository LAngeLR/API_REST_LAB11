package edu.pucp.gtics.lab11_gtics_20232.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Getter
@Setter
@Entity
@Table(name = "distribuidoras")
public class Distribuidoras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 0, message = "Distribuidora no puede estar vacío")
    private int iddistribuidora;

    @Size(min=3, max = 50, message = "Debe contener entre 3 y 50 caracteres")
    private String nombre;

    @Size(min=3, max = 198, message = "Debe contener entre 3 y 198 caracteres")
    private String descripcion;

    @Size(min=3, max = 198, message = "Debe contener entre 3 y 198 caracteres")
    @Pattern(regexp = "^(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", message = "Debe ser una URL http o https")
    private String web;

    @Min(value = 1800, message = "Debe ser mayor que o igual a 1800")
    @Max(value = 2100, message = "Debe ser menor que 2100")
    @NotNull(message = "Coloque un número")
    private Integer fundacion = 1800;

    @ManyToOne
    @JoinColumn(name = "idsede")
    @Valid
    private Paises pais;


}
