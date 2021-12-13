package co.edu.uniquindio.software.safepet.persistencia.entidades;

public class Raza {

    private String codigo;
    private String nombre;

    public Raza(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Raza() {

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
}
