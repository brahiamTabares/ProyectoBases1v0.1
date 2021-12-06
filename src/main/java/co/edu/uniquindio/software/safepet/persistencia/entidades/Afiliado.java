package co.edu.uniquindio.software.safepet.persistencia.entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Afiliado extends Usuario{

    private List<Plan> Planes;
    private String telefono;


    public Afiliado() {
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


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
