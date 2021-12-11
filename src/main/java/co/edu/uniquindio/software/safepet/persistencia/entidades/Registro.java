package co.edu.uniquindio.software.safepet.persistencia.entidades;

import java.util.Date;

public class Registro {

   private String codigo;
   private String observaciones;
   private String concepto;
   private String decisiones;
   private Date fecharegistro;
   private String examenes_codigo;
   private String historiaclinica_mascota_id;

    public Registro(String codigo, String observaciones, String concepto, String decisiones, Date fecharegistro, String examenes_codigo, String historiaclinica_mascota_id) {
        this.codigo = codigo;
        this.observaciones = observaciones;
        this.concepto = concepto;
        this.decisiones = decisiones;
        this.fecharegistro = fecharegistro;
        this.examenes_codigo = examenes_codigo;
        this.historiaclinica_mascota_id = historiaclinica_mascota_id;
    }

    public Registro() {

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getConcepto() {
        return this.concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getDecisiones() {
        return decisiones;
    }

    public void setDecisiones(String decisiones) {
        this.decisiones = decisiones;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public String getExamenes_codigo() {
        return examenes_codigo;
    }

    public void setExamenes_codigo(String examenes_codigo) {
        this.examenes_codigo = examenes_codigo;
    }

    public String getHistoriaclinica_mascota_id() {
        return historiaclinica_mascota_id;
    }

    public void setHistoriaclinica_mascota_id(String historiaclinica_mascota_id) {
        this.historiaclinica_mascota_id = historiaclinica_mascota_id;
    }
}
