package co.edu.uniquindio.software.safepet.gui.beans.reportes;

import co.edu.uniquindio.software.safepet.logica.ReporteServicio;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ReporteMascotasPorPlanBean extends ReporteBean{
    @Inject
    private ReporteServicio reporteServicio;


    @PostConstruct
    public void inicializar(){
        label = "Plan";
        titulo = "# Mascotas por Plan";
        datos = reporteServicio.generarReporteMascotasPorPlan();
        createBarModel();
    }
}
