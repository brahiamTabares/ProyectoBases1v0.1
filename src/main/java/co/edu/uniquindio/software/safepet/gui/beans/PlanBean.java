package co.edu.uniquindio.software.safepet.gui.beans;

import co.edu.uniquindio.software.safepet.gui.beans.seguridad.SeguridadBean;
import co.edu.uniquindio.software.safepet.logica.MascotaBO;
import co.edu.uniquindio.software.safepet.logica.PlanBO;
import co.edu.uniquindio.software.safepet.logica.ServicioBO;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Mascota;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Plan;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Servicio;

import javax.el.MethodExpression;
import javax.faces.annotation.ManagedProperty;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named
@ViewScoped
public class PlanBean extends PrimeFacesCrudBean<Plan,String, PlanBO> {
    @Inject
    private MascotaBO mascotaBO;
    @Inject
    private ServicioBO servicioBO;
    @Inject
    private SeguridadBean seguridadBean;

    private List<Servicio> servicios;
    private String idServicio;

    @Inject
    public PlanBean(PlanBO bo) {
        super(bo);
        servicios = new ArrayList<>();
    }

    private Mascota mascota;


    public String getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    @Override
    public void newEntity() {
        super.newEntity();
        servicios.clear();
        //servicios = new Servicio[];
    }

    @Override
    public void setSelectedEntity(Plan selectedEntity) {
        super.setSelectedEntity(selectedEntity);
//        servicios = selectedEntity.getServicios().toArray(new Servicio[0]);
        servicios.clear();
        servicios.addAll( selectedEntity.getServicios() );
    }

    public void save(){
        selectedEntity.getServicios().clear();
//        selectedEntity.getServicios().addAll(Arrays.asList(servicios));
        selectedEntity.getServicios().addAll(servicios);
        selectedEntity.setEmpleadoSafepet_id( seguridadBean.getId() );
        super.save();
    }

    public void selectionChanged()  {
        if( servicios != null ){
            int valor = 0;
            for (Servicio a:servicios) {
                if( a != null ) {
                    valor += a.getValor();
                }
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
        mascota.setPlan_id(selectedEntity.getId());
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


    public void addServicio() {
        try {
            Servicio servicio = servicioBO.find(idServicio);
            if (!servicios.contains(servicio) && servicio != null) {
                servicios.add(servicio);
            }
            selectionChanged();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeServicio(Servicio servicio) {
        servicios.remove(servicio);
        selectionChanged();
    }
}