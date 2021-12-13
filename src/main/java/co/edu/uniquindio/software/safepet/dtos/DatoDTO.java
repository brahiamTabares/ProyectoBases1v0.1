package co.edu.uniquindio.software.safepet.dtos;

import java.io.Serializable;

public class DatoDTO implements Serializable {
    private String texto;
    private Number valor;

    public DatoDTO(String texto, Number valor) {
        this.texto = texto;
        this.valor = valor;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Number getValor() {
        return valor;
    }

    public void setValor(Number valor) {
        this.valor = valor;
    }
}
