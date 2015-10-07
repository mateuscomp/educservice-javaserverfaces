package br.ufpb.dcx.jsf.converter;
import javax.faces.convert.Converter;

import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

import br.ufpb.dcx.model.Usuario;

@RooJsfConverter(entity = Usuario.class)
public class UsuarioConverter implements Converter {
}
