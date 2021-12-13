package co.edu.uniquindio.software.safepet.gui.beans.util;

import co.edu.uniquindio.software.safepet.logica.TipoCentroBO;
import co.edu.uniquindio.software.safepet.persistencia.entidades.TipoCentro;


import javax.enterprise.context.ApplicationScoped;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
@FacesConverter(value = "tipoCentroConverter",managed = true)
public class TipoCentroConverter extends EntidadConverter<TipoCentro> {
	@Inject
	private TipoCentroBO bo;

	@Override
	protected TipoCentro findById(String id) {return bo.find(id);
	}

	@Override
	protected String enityToString(TipoCentro entity) {
		return entity.getCodigo().toString();
	}
}