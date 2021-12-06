package co.edu.uniquindio.software.safepet.gui.beans.util;

import co.edu.uniquindio.software.safepet.logica.TipoMascotaBO;
import co.edu.uniquindio.software.safepet.persistencia.entidades.TipoMascota;

import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@FacesConverter(value = "tipoMascotaConverter",managed = true)
public class TipoMascotaConverter extends EntidadConverter<TipoMascota> {
	@Inject
	private TipoMascotaBO bo;

	@Override
	protected TipoMascota findById(String id) {
		return bo.find(Integer.parseInt(id));
	}

	@Override
	protected String enityToString(TipoMascota entity) {
		return entity.getId().toString();
	}
}