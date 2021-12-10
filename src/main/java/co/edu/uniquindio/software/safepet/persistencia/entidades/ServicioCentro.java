package co.edu.uniquindio.software.safepet.persistencia.entidades;

public class ServicioCentro {

    private String idser;
   private String  idcen;

    public ServicioCentro(String idser, String idcen) {
        this.idser = idser;
        this.idcen = idcen;
    }

    public String getIdser() {
        return idser;
    }

    public void setIdser(String idser) {
        this.idser = idser;
    }

    public String getIdcen() {
        return idcen;
    }

    public void setIdcen(String idcen) {
        this.idcen = idcen;
    }
}
