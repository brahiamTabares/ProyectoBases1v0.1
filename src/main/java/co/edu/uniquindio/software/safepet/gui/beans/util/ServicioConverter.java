package co.edu.uniquindio.software.safepet.gui.beans.util;

import co.edu.uniquindio.software.safepet.logica.ServicioBO;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Servicio;

import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@FacesConverter(value = "servicioConverter",managed = true)
public class ServicioConverter extends EntidadConverter<Servicio> {
	@Inject
	private ServicioBO bo;

	@Override
	protected Servicio findById(String id) {
		return bo.find(Integer.parseInt(id));
	}

	@Override
	protected String enityToString(Servicio entity) {
		return entity.getId().toString();
	}
}