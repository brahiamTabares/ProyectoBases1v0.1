package co.edu.uniquindio.software.safepet.gui.beans;

import co.edu.uniquindio.software.safepet.logica.MascotaBO;
import co.edu.uniquindio.software.safepet.logica.PlanBO;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Mascota;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Plan;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Servicio;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class PlanBean extends PrimeFacesCrudBean<Plan,Integer, PlanBO> {
    @Inject
    private MascotaBO mascotaBO;

    @Inject
    public PlanBean(PlanBO bo) {
        super(bo);
    }

    private Mascota mascota;

    private List<Servicio> servicios;


    public List<Servicio> getServicios() {
        return servicios;
    }


    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }


    @Override
    public void newEntity() {
        super.newEntity();
        servicios = new ArrayList<>();
    }

    @Override
    public void setSelectedEntity(Plan selectedEntity) {
        super.setSelectedEntity(selectedEntity);
        servicios = new ArrayList<>();
        servicios.addAll( selectedEntity.getServicios() );
    }

    public void save(){
        selectedEntity.getServicios().clear();
        selectedEntity.getServicios().addAll(servicios);
        super.save();
    }

    public void subjectSelectionChanged(final AjaxBehaviorEvent event)  {
        if( servicios != null ){
            int valor = 0;
            for (Servicio a:servicios) {
                valor += a.getValor();
            }
            selectedEntity.setMensualidad(valor);

            if( servicios.size() <= 2 ){
                selectedEntity.setCopago(10000);
            } else if( servicios.size() <= 6 ){
                selectedEntity.setCopago(5000);
            } else {
                selectedEntity.setCopago(2000);
            }
        }
    }

    public void crearMascota(){
        mascota.setPlan(selectedEntity);
        selectedEntity.getMascotas().add(
                mascotaBO.create(mascota)
                // se llama el nuevo mètodo aquì para que afecte el valor de la mensualidad por el aumento de mascotas
        );
    }

    public void eliminarMascota() {
        mascotaBO.delete(mascota);
//        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        selectedEntity.getMascotas().remove(mascota);
        // se llama el nuevo mètodo aquì para que afecte el valor de la mensualidad por la disminuciòn de mascota
    }

    public void newMascota(){
        mascota = new Mascota();
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }


}