package co.edu.uniquindio.software.safepet.logica;

import co.edu.uniquindio.software.safepet.persistencia.entidades.EmpleadoSafePet;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class EmpleadoSafePetBO extends GenericBO<EmpleadoSafePet,String>{

    @Override
    public EmpleadoSafePet create(EmpleadoSafePet entity) {
        return null;
    }

    @Override
    public void delete(EmpleadoSafePet entity) {

    }

    @Override
    public EmpleadoSafePet find(String id) {
        return null;
    }

    @Override
    public EmpleadoSafePet update(EmpleadoSafePet entity) {
        return null;
    }

    @Override
    public List<EmpleadoSafePet> findAll() {
        return null;
    }
}
