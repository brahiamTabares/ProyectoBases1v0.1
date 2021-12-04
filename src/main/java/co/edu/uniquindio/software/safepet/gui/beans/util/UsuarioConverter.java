package co.edu.uniquindio.software.safepet.gui.beans.util;

import co.edu.uniquindio.software.safepet.logica.UsuarioBO;
import co.edu.uniquindio.software.safepet.persistencia.entidades.Usuario;

import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@FacesConverter(value = "usuarioConverter",managed = true)
public class UsuarioConverter extends EntidadConverter<Usuario> {
	@Inject
	private UsuarioBO bo;

	@Override
	protected Usuario findById(String id) {
		return bo.find(id);
	}

	@Override
	protected String enityToString(Usuario entity) {
		return entity.getId();
	}
}