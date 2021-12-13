package co.edu.uniquindio.software.safepet.gui.beans;

import co.edu.uniquindio.software.safepet.logica.UsuarioBO;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Usuario;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class UsuarioBean extends PrimeFacesCrudBean<Usuario,String, UsuarioBO> {
    @Inject
    public UsuarioBean(UsuarioBO bo) {
        super(bo);
    }


}
