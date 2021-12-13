package co.edu.uniquindio.software.safepet.gui.beans.util;

import co.edu.uniquindio.software.safepet.logica.ServicioBO;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Servicio;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named("servicioConverter")
@ApplicationScoped
@FacesConverter(value = "servicioConverter",managed = true,forClass = Servicio.class)
public class ServicioConverter extends EntidadConverter<Servicio> {
	@Inject
	private ServicioBO bo;

	@Override
	protected Servicio findById(String id) {
		if( bo == null ){
			bo = CDI.current().select(ServicioBO.class).get();
		}
		return bo.find(id);
	}

	@Override
	protected String enityToString(Servicio entity) {
		return entity.getId().toString();
	}
}