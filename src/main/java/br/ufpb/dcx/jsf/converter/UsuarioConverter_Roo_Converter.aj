// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.ufpb.dcx.jsf.converter;

import br.ufpb.dcx.jsf.converter.UsuarioConverter;
import br.ufpb.dcx.model.Usuario;
import br.ufpb.dcx.service.UsuarioService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;

privileged aspect UsuarioConverter_Roo_Converter {
    
    declare @type: UsuarioConverter: @FacesConverter("br.ufpb.dcx.jsf.converter.UsuarioConverter");
    
    @Autowired
    UsuarioService UsuarioConverter.usuarioService;
    
    public Object UsuarioConverter.getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return usuarioService.findUsuario(id);
    }
    
    public String UsuarioConverter.getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Usuario ? ((Usuario) value).getId().toString() : "";
    }
    
}
