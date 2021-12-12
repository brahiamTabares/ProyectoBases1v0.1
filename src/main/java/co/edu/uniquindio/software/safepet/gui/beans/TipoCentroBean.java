package co.edu.uniquindio.software.safepet.gui.beans;

import co.edu.uniquindio.software.safepet.logica.TipoCentroBO;
import co.edu.uniquindio.software.safepet.logica.TipoMascotaBO;
import co.edu.uniquindio.software.safepet.persistencia.entidades.TipoCentro;
import co.edu.uniquindio.software.safepet.persistencia.entidades.TipoMascota;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class TipoCentroBean extends PrimeFacesCrudBean<TipoCentro,String, TipoCentroBO> {
    @Inject
    public TipoCentroBean(TipoCentroBO bo) {
        super(bo);
    }


}
