package co.edu.uniquindio.software.safepet.persistencia.entidades;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {
    private String id;
    private String nombre;
    private String contrasenia;
    private String telefono;

    public Usuario() {
    }

    public Usuario(String id, String nombre, String contrasenia, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(getId(), usuario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}