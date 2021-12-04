package co.edu.uniquindio.software.safepet.gui.beans.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public abstract class EntidadConverter<E> implements Converter<E> {
	protected abstract E findById(String id);
	protected abstract String enityToString(E entity);

	@Override
	public E getAsObject(FacesContext facesContext, UIComponent componente, String id) {
		E entidad = null;
		if (id != null && !"".equals(id.trim())) {
			try {
				entidad = findById( id );
			} catch (Exception e) {
				e.printStackTrace();
				throw new ConverterException(
						new FacesMessage( componente.getClientId() + ":" + 	"Error al convertir el elemento"),
						e
				);
			}
		}
		return entidad;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent componente, E entidad) {
		return  entidad != null ? enityToString(entidad) : "";
	}
}