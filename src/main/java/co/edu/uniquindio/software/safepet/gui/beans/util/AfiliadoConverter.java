package co.edu.uniquindio.software.safepet.gui.beans.util;

import co.edu.uniquindio.software.safepet.logica.AfiliadoBO;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Afiliado;

import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@FacesConverter(value = "afiliadoConverter",managed = true)
public class AfiliadoConverter extends EntidadConverter<Afiliado> {
	@Inject
	private AfiliadoBO bo;

	@Override
	protected Afiliado findById(String id) {
		return bo.find(id);
	}

	@Override
	protected String enityToString(Afiliado entity) {
		return entity.getId();
	}
}