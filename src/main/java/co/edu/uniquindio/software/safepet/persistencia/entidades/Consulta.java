package co.edu.uniquindio.software.safepet.persistencia.entidades;

import java.util.Date;
import java.util.Objects;

public class Consulta {
    private String codigo;
    private Date fecha_Cita;
    private String descripcion;
    private String centroServicio_id;
    private String mascota_id;

    public Consulta() {
        this.codigo = codigo;
        this.fecha_Cita = fecha_Cita;
        this.descripcion = descripcion;
        this.centroServicio_id = centroServicio_id;
        this.mascota_id = mascota_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Consulta)) return false;
        Consulta consulta = (Consulta) o;
        return Objects.equals(codigo, consulta.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFecha_Cita() {
        return fecha_Cita;
    }

    public void setFecha_Cita(Date fecha_Cita) {
        this.fecha_Cita = fecha_Cita;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCentroServicio_id() {
        return centroServicio_id;
    }

    public void setCentroServicio_id(String centroServicio_id) {
        this.centroServicio_id = centroServicio_id;
    }

    public String getMascota_id() {
        return mascota_id;
    }

    public void setMascota_id(String mascota_id) {
        this.mascota_id = mascota_id;
    }
}




