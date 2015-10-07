package br.ufpb.dcx.jsf;

import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

import br.ufpb.dcx.jsf.util.EducServiceJsfUtil;
import br.ufpb.dcx.model.Usuario;

@RooSerializable
@RooJsfManagedBean(entity = Usuario.class, beanName = "sessaoBean")
public class SessaoBean {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	public String getNicknameUsuarioAutenticado(){
		pesquisarUsuario();
		
		if(usuario != null){
			return usuario.getNickName();
		}
		return null;
	}

	
	public String getPerfilUsuarioAutenticado(){
		pesquisarUsuario();
		
		if(usuario != null){
			return this.usuario.getPerfil().getNome();
		}
		
		return null;
	}
	
	private void pesquisarUsuario() {
		if(usuario == null){
			usuario = EducServiceJsfUtil.getUsuarioDaSession();
		}
	}
	
	public String sair(){
		System.out.println("############################################...");
		System.out.println("Chamou logoff...");
		System.out.println("############################################...");
	
		return "/main.jsf";
	}
	
}
