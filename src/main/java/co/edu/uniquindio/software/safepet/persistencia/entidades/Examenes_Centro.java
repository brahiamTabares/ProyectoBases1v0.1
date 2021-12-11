package co.edu.uniquindio.software.safepet.persistencia.entidades;

public class Examenes_Centro {

    private String centroServicio_id;
    private String examenes_codigo;

    public Examenes_Centro(String centroServicio_id, String examenes_codigo) {
        this.centroServicio_id = centroServicio_id;
        this.examenes_codigo = examenes_codigo;
    }

    public String getCentroServicio_id() {
        return centroServicio_id;
    }

    public void setCentroServicio_id(String centroServicio_id) {
        this.centroServicio_id = centroServicio_id;
    }

    public String getExamenes_codigo() {
        return examenes_codigo;
    }

    public void setExamenes_codigo(String examenes_codigo) {
        this.examenes_codigo = examenes_codigo;
    }
}

