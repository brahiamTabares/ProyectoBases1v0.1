package co.edu.uniquindio.software.safepet.persistencia.entidades;

public class CentroServicio extends Usuario{


    private String  tipoCentro_codigo;
    private TipoCentro tipoCentro;


    public String getTipoCentro_codigo() {
        return tipoCentro_codigo;
    }

    public void setTipoCentro_codigo(String tipoCentro_codigo) {
        this.tipoCentro_codigo = tipoCentro_codigo;
    }

    public TipoCentro getTipoCentro() {
        return tipoCentro;
    }

    public void setTipoCentro(TipoCentro tipoCentro) {
        this.tipoCentro = tipoCentro;
    }
}
