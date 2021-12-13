package co.edu.uniquindio.software.safepet.gui.beans.reportes;

import co.edu.uniquindio.software.safepet.logica.ReporteServicio;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ReporteMascotasPorTipoBean extends ReporteBean{
    @Inject
    private ReporteServicio reporteServicio;


    @PostConstruct
    public void inicializar(){
        label = "Raza";
        titulo = "# Mascotas por Tipo";
        datos = reporteServicio.generarReporteMascotasPorTipo();
        createBarModel();
    }
}
