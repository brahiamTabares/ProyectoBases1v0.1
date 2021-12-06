package co.edu.uniquindio.software.safepet.gui.beans;

import co.edu.uniquindio.software.safepet.logica.ServicioBO;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Servicio;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ServicioBean extends PrimeFacesCrudBean<Servicio,Integer, ServicioBO> {
    @Inject
    public ServicioBean(ServicioBO bo) {
        super(bo);
    }


}
