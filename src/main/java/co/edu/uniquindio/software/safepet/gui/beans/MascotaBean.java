package co.edu.uniquindio.software.safepet.gui.beans;

import co.edu.uniquindio.software.safepet.logica.MascotaBO;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Mascota;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Plan;
import org.primefaces.PrimeFaces;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class MascotaBean extends PrimeFacesCrudBean<Mascota,Integer, MascotaBO> {
    @Inject
    public MascotaBean(MascotaBO bo) {
        super(bo);
    }

    private Plan plan;

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        System.out.println("PLAN "+plan.getId());
        this.plan = plan;
    }

    @Override
    public void save(){
        System.out.println("PLAN SET"+plan.getId());
        selectedEntity.setPlan(plan);
        super.save();
        PrimeFaces.current().executeScript("PF('manageMascotasDialog').hide()");
    }

    @Override
    public void delete() {
        super.delete();
        plan.getMascotas().remove(selectedEntity);
    }
}
