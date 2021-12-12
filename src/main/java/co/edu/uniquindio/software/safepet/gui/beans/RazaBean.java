package co.edu.uniquindio.software.safepet.gui.beans;

import co.edu.uniquindio.software.safepet.logica.RazaBO;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Raza;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class RazaBean extends PrimeFacesCrudBean<Raza,String, RazaBO> {
    @Inject
    public RazaBean(RazaBO bo) {
        super(bo);
    }


}
