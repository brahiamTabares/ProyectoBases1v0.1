package co.edu.uniquindio.software.safepet.persistencia.entidades;

public class TipoMascota {

    private String codigo;
    private  String tipo;

    public TipoMascota(String codigo, String nombre) {
        this.codigo = codigo;
        this.tipo = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
