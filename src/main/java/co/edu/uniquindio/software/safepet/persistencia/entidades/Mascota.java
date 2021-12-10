package co.edu.uniquindio.software.safepet.persistencia.entidades;

import java.util.Date;
import java.util.Objects;

public class Mascota {

      private   Integer id;
      private String nombre;
      private Date fecha_nacimiento;
    private String  genero;
    private String   plan_id;
    private Integer tipomascota_id;
    private String raza_codigo ;


    public Mascota(Integer id, String nombre, Date fecha_nacimiento, String genero, String plan_id, Integer tipomascota_id, String raza_codigo) {
        this.id = id;
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.genero = genero;
        this.plan_id = plan_id;
        this.tipomascota_id = tipomascota_id;
        this.raza_codigo = raza_codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mascota)) return false;
        Mascota mascota = (Mascota) o;
        return id.equals(mascota.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public Integer getTipomascota_id() {
        return tipomascota_id;
    }

    public void setTipomascota_id(Integer tipomascota_id) {
        this.tipomascota_id = tipomascota_id;
    }

    public String getRaza_codigo() {
        return raza_codigo;
    }

    public void setRaza_codigo(String raza_codigo) {
        this.raza_codigo = raza_codigo;
    }


}
