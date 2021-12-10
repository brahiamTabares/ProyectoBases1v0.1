package co.edu.uniquindio.software.safepet.persistencia.entidades;

import java.util.Date;

public class PlanServicio {
    private  String id ;
    private Date fechaServicio;
    private String servicio_idcs;
    private String    servicioc_id;
    private String   servicioc_idser;
    private String servicioc_idcen;

    public PlanServicio(String id, Date fechaServicio, String servicio_idcs, String servicioc_id, String servicioc_idser, String servicioc_idcen) {
        this.id = id;
        this.fechaServicio = fechaServicio;
        this.servicio_idcs = servicio_idcs;
        this.servicioc_id = servicioc_id;
        this.servicioc_idser = servicioc_idser;
        this.servicioc_idcen = servicioc_idcen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(Date fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    public String getServicio_idcs() {
        return servicio_idcs;
    }

    public void setServicio_idcs(String servicio_idcs) {
        this.servicio_idcs = servicio_idcs;
    }

    public String getServicioc_id() {
        return servicioc_id;
    }

    public void setServicioc_id(String servicioc_id) {
        this.servicioc_id = servicioc_id;
    }

    public String getServicioc_idser() {
        return servicioc_idser;
    }

    public void setServicioc_idser(String servicioc_idser) {
        this.servicioc_idser = servicioc_idser;
    }

    public String getServicioc_idcen() {
        return servicioc_idcen;
    }

    public void setServicioc_idcen(String servicioc_idcen) {
        this.servicioc_idcen = servicioc_idcen;
    }
}
