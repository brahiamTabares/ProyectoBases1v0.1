package co.edu.uniquindio.software.safepet.persistencia.entidades;

public class TipoMascota {

    private String id;
    private  String tipo;

    public TipoMascota(String id, String nombre) {
        this.id = id;
        this.tipo = nombre;
    }

    public TipoMascota() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
