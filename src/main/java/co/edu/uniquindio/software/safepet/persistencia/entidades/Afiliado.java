package co.edu.uniquindio.software.safepet.persistencia.entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Afiliado extends Usuario{

    private List<Plan> Planes;



    public Afiliado() {
    }

    public List<Plan> getPlanes() {
        return Planes;
    }

    public void setPlanes(List<Plan> planes) {
        Planes = planes;
    }
}
