package co.edu.uniquindio.software.safepet.gui.beans;

import co.edu.uniquindio.software.safepet.logica.CentroServicioBO;
import co.edu.uniquindio.software.safepet.persistencia.entidades.CentroServicio;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Servicio;
import co.edu.uniquindio.software.safepet.persistencia.entidades.TipoMascota;


import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class CentroServicioBean extends PrimeFacesCrudBean<CentroServicio,String, CentroServicioBO> {
    @Inject
    public CentroServicioBean(CentroServicioBO bo) {
        super(bo);
    }



}
