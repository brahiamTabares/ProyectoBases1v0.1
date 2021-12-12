package co.edu.uniquindio.software.safepet.gui.beans;

import co.edu.uniquindio.software.safepet.logica.ExamenBO;
import co.edu.uniquindio.software.safepet.logica.RazaBO;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Examen;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Raza;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ExamenBean extends PrimeFacesCrudBean<Examen,String, ExamenBO> {
    @Inject
    public ExamenBean(ExamenBO bo) {
        super(bo);
    }


}
