package co.edu.uniquindio.software.safepet.persistencia.entidades;

import java.util.ArrayList;
import java.util.List;

public class Plan {

    private String  id ;
    private  double mensualidad;
    private  double copago;
    private String afiliado_id;
    private String empleadoSafepet_id;
    private List<Mascota> mascotas;
    private List<Servicio>servicios;
    private Afiliado afiliado;


    public Plan(String id, double mensualidad, double copago, String afiliado_id, String empleadoSafepet_id) {
        this();
        this.id = id;
        this.mensualidad = mensualidad;
        this.copago = copago;
        this.afiliado_id = afiliado_id;
        this.empleadoSafepet_id = empleadoSafepet_id;
    }

    public Plan() {
        servicios = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMensualidad() {
        return mensualidad;
    }

    public void setMensualidad(double mensualidad) {
        this.mensualidad = mensualidad;
    }

    public double getCopago() {
        return copago;
    }

    public void setCopago(double copago) {
        this.copago = copago;
    }

    public String getAfiliado_id() {
        return afiliado_id;
    }

    public void setAfiliado_id(String afiliado_id) {
        this.afiliado_id = afiliado_id;
    }

    public String getEmpleadoSafepet_id() {
        return empleadoSafepet_id;
    }

    public void setEmpleadoSafepet_id(String empleadoSafepet_id) {
        this.empleadoSafepet_id = empleadoSafepet_id;
    }
    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public Afiliado getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(Afiliado afiliado) {
        this.afiliado = afiliado;
        this.afiliado_id = afiliado.getId();
    }
}
