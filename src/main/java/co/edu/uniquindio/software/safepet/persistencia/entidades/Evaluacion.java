package co.edu.uniquindio.software.safepet.persistencia.entidades;

import java.util.Objects;

public class Evaluacion {



    private String id_evaluacion;
    private Integer puntacion;
    private String planservicio_servicio_idcs;
    private String planservicio_servicioc_id;
    private String  afiliado_id;
    private String  planservicio_id;


    public Evaluacion() {
        this.id_evaluacion = id_evaluacion;
        this.puntacion = puntacion;
        this.planservicio_servicio_idcs = planservicio_servicio_idcs;
        this.planservicio_servicioc_id = planservicio_servicioc_id;
        this.afiliado_id = afiliado_id;
        this.planservicio_id=planservicio_id;
    }


    public String getId_evaluacion() {
        return id_evaluacion;
    }

    public void setId_evaluacion(String id_evaluacion) {
        this.id_evaluacion = id_evaluacion;
    }

    public Integer getPuntacion() {
        return puntacion;
    }

    public void setPuntacion(Integer puntacion) {
        this.puntacion = puntacion;
    }

    public String getPlanservicio_servicio_idcs() {
        return planservicio_servicio_idcs;
    }



    public void setPlanservicio_servicio_idcs(String planservicio_servicio_idcs) {
        this.planservicio_servicio_idcs = planservicio_servicio_idcs;
    }

    public String getPlanservicio_servicioc_id() {
        return planservicio_servicioc_id;
    }

    public void setPlanservicio_servicioc_id(String planservicio_servicioc_id) {
        this.planservicio_servicioc_id = planservicio_servicioc_id;
    }

    public String getAfiliado_id() {
        return afiliado_id;
    }

    public void setAfiliado_id(String afiliado_id) {
        this.afiliado_id = afiliado_id;
    }

    public String getPlanservicio_id() {
        return planservicio_id;
    }

    public void setPlanservicio_id(String planservicio_id) {
        this.planservicio_id = planservicio_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evaluacion)) return false;
        Evaluacion that = (Evaluacion) o;
        return Objects.equals(id_evaluacion, that.id_evaluacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_evaluacion);
    }
}
