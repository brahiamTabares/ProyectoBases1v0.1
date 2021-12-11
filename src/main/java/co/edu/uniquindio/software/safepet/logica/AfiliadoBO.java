package co.edu.uniquindio.software.safepet.logica;

import co.edu.uniquindio.software.safepet.persistencia.entidades.Afiliado;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class AfiliadoBO extends GenericBO<Afiliado,String>{

    @Override
    public Afiliado create(Afiliado entity) {
        return null;
    }

    @Override
    public void delete(Afiliado entity) {

    }

    @Override
    public Afiliado find(String id) {
        return null;
    }

    @Override
    public Afiliado update(Afiliado entity) {
        return null;
    }

    @Override
    public List<Afiliado> findAll() {
        return null;
    }
}
