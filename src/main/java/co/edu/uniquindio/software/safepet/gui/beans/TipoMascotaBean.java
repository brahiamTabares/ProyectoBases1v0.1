package co.edu.uniquindio.software.safepet.gui.beans;

import co.edu.uniquindio.software.safepet.logica.TipoMascotaBO;
import co.edu.uniquindio.software.safepet.persistencia.entidades.TipoMascota;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class TipoMascotaBean extends PrimeFacesCrudBean<TipoMascota,Integer, TipoMascotaBO> {
    @Inject
    public TipoMascotaBean(TipoMascotaBO bo) {
        super(bo);
    }


}
