package co.edu.uniquindio.software.safepet.gui.beans;

import co.edu.uniquindio.software.safepet.logica.CentroServicioBO;
import co.edu.uniquindio.software.safepet.persistencia.entidades.CentroVeterinario;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class CentroVeterinarioBean extends PrimeFacesCrudBean<CentroVeterinario,String, CentroServicioBO> {
    @Inject
    public CentroVeterinarioBean(CentroServicioBO bo) {
        super(bo);
    }


}
