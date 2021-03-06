package co.edu.uniquindio.software.safepet.persistencia.entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Servicio implements Serializable {

    private String id ;
 private String nombre;
  private double  valor;
    private List<Plan> plan;


    public Servicio(String id, String nombre, double valor) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
    }

    public Servicio() {

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

    public List<Plan> getPlan() {
        return plan;
    }

    public void setPlan(List<Plan> plan) {
        this.plan = plan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Servicio)) return false;
        Servicio servicio = (Servicio) o;
        return getId().equals(servicio.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
