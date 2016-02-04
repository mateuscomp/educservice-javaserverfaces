package br.ufpb.dcx.jsf;

import java.io.IOException;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

import br.ufpb.dcx.jsf.util.EducServiceJsfUtil;
import br.ufpb.dcx.model.Usuario;
import br.ufpb.dcx.service.EducServiceException;
import br.ufpb.dcx.service.UsuarioService;

@RooSerializable
@RooJsfManagedBean(entity = Usuario.class, beanName = "esqueciMinhaSenhaBean")
public class EsqueciMinhaSenhaBean {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioService usuarioService;

	private String nickname;
	private String email;
	
	private String senhaGeradaInput;
	private String novaSenha;
	private Usuario usuario;
	
	public void recuperarSenha() {
		try {
			usuario = usuarioService.recuperarSenha(nickname, email);
			EducServiceJsfUtil.lancarMensagemInformativa("Uma nova senha foi enviada para o e-mail do usuário!");
		} catch (EducServiceException e) {
			usuario = null;
			EducServiceJsfUtil.lancarMensagemDeErro(e.getMessage());
		}
	}
	
	public void redirecionarPaginaInicial(){
		try {
			EducServiceJsfUtil.obterResponse().sendRedirect("main.jsf");
			FacesContext.getCurrentInstance().responseComplete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getSenhaGeradaInput() {
		return senhaGeradaInput;
	}

	public void setSenhaGeradaInput(String senhaGeradaInput) {
		this.senhaGeradaInput = senhaGeradaInput;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}