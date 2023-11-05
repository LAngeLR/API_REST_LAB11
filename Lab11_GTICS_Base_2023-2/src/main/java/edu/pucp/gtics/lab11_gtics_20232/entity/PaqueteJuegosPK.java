package edu.pucp.gtics.lab11_gtics_20232.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class PaqueteJuegosPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "idcarritocompras")
    private CarritoCompras carritoCompras;

    @ManyToOne
    @JoinColumn(name = "idjuegoseleccionado")
    private Juegos juegos;


    public PaqueteJuegosPK() {
    }
}
