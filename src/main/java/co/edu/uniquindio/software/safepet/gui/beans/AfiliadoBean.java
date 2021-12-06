package co.edu.uniquindio.software.safepet.gui.beans;

import co.edu.uniquindio.software.safepet.logica.AfiliadoBO;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Afiliado;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class AfiliadoBean extends PrimeFacesCrudBean<Afiliado,String, AfiliadoBO> {
    @Inject
    public AfiliadoBean(AfiliadoBO bo) {
        super(bo);
    }


}
