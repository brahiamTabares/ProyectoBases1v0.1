package co.edu.uniquindio.software.safepet.persistencia.entidades;

public class TipoCentro {
    private  String codigo;
    private String nombre;

    public TipoCentro(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public TipoCentro() {

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
