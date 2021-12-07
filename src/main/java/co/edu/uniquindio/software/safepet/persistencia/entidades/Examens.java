package co.edu.uniquindio.software.safepet.persistencia.entidades;

import java.util.Objects;

public class Examens {
    public String getCodigo() {
        return codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Examens)) return false;
        Examens examens = (Examens) o;
        return Objects.equals(codigo, examens.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    public Examens(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
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

    private  String codigo;
    private String  nombre;
}
