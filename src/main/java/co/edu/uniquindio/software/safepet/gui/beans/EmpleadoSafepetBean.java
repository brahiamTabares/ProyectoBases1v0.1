package co.edu.uniquindio.software.safepet.gui.beans;

import co.edu.uniquindio.software.safepet.logica.EmpleadoSafePetBO;
import co.edu.uniquindio.software.safepet.persistencia.entidades.EmpleadoSafePet;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class EmpleadoSafepetBean extends PrimeFacesCrudBean<EmpleadoSafePet,String, EmpleadoSafePetBO> {
    @Inject
    public EmpleadoSafepetBean(EmpleadoSafePetBO bo) {
        super(bo);
    }


}
