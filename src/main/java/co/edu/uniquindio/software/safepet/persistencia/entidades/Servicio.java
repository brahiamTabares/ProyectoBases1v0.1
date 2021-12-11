package co.edu.uniquindio.software.safepet.persistencia.entidades;

import java.io.Serializable;

public class Servicio implements Serializable {

    private String id ;
 private String nombre;
  private double  valor;

    public Servicio(String id, String nombre, double valor) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
