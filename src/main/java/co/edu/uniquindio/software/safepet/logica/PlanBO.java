package co.edu.uniquindio.software.safepet.logica;

import co.edu.uniquindio.software.safepet.persistencia.entidades.Plan;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PlanBO implements GenericBO<Plan,String>{
    @Override
    public Plan create(Plan entity) {
        return null;
    }

    @Override
    public void delete(Plan entity) {

    }

    @Override
    public Plan find(String id) {

        return null;
    }

    @Override
    public Plan update(Plan entity) {
        return null;
    }

    @Override
    public List<Plan> findAll() {
        return null;
    }
}
