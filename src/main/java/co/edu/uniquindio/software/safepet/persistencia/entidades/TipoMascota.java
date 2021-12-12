package co.edu.uniquindio.software.safepet.persistencia.entidades;

import java.util.List;

public class TipoMascota {

    private String id;
    private  String tipo;
    private List<Mascota> Mascotas;

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

    public List<Mascota> getMascotas() {
        return Mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        Mascotas = mascotas;
    }
}
