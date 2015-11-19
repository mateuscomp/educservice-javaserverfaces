package br.ufpb.dcx.jsf.converter;
import java.io.Serializable;

import javax.faces.convert.Converter;

import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

import br.ufpb.dcx.model.Usuario;

@RooJsfConverter(entity = Usuario.class)
public class UsuarioConverter implements Converter, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
