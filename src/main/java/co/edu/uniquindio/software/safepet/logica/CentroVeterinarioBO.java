package co.edu.uniquindio.software.safepet.logica;

import co.edu.uniquindio.software.safepet.persistencia.entidades.CentroVeterinario;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class CentroVeterinarioBO extends GenericBO<CentroVeterinario,String>{

    @Override
    public CentroVeterinario create(CentroVeterinario entity) {
        return null;
    }

    @Override
    public void delete(CentroVeterinario entity) {

    }

    @Override
    public CentroVeterinario find(String id) {
        return null;
    }

    @Override
    public CentroVeterinario update(CentroVeterinario entity) {
        return null;
    }

    @Override
    public List<CentroVeterinario> findAll() {
        return null;
    }
}
