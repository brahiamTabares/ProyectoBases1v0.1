package co.edu.uniquindio.software.safepet.persistencia.entidades;



import java.util.Date;
import java.util.Objects;

public class HistoriaClinica {


    private String nombre;
    private String sexo;
    private Date fechaIngreso;
    private Date fechaSalida;
    private String mascota_id;


    public HistoriaClinica(String nombre, String sexo, Date fechaIngreso, Date fechaSalida, String mascota_id) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.mascota_id = mascota_id;
    }

    public HistoriaClinica() {

    }


    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HistoriaClinica)) return false;
        HistoriaClinica that = (HistoriaClinica) o;
        return mascota_id.equals(that.mascota_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mascota_id);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getMascota_id() {
        return mascota_id;
    }

    public void setMascota_id(String mascota_id) {
        this.mascota_id = mascota_id;
    }
}
