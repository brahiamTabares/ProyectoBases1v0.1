package co.edu.uniquindio.software.safepet.persistencia.entidades;

import java.util.Objects;

public class Examen {

    private  String codigo;
    private String  nombre;


    public Examen() {
        this.codigo = codigo;
        this.nombre = nombre;
    }


    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Examen)) return false;
        Examen examens = (Examen) o;
        return Objects.equals(codigo, examens.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

}
