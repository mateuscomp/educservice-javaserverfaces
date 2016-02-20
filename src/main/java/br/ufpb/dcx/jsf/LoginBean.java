package br.ufpb.dcx.jsf;

import java.io.IOException;

import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

import br.ufpb.dcx.jsf.util.EducServiceJsfUtil;
import br.ufpb.dcx.model.PerfilEnum;
import br.ufpb.dcx.model.Usuario;
import br.ufpb.dcx.service.UsuarioService;

@RooSerializable
@RooJsfManagedBean(entity = Usuario.class, beanName = "loginBean")
@ViewScoped
public class LoginBean {

	@Autowired
	private UsuarioService usuarioService;

	private String login;
	private String senha;

	public void autenticar() {
		Usuario usuario = this.usuarioService.pesquisarUsuarioPorEmailOrNickNameAndSenha(login, senha);
		if (usuario != null) {
			EducServiceJsfUtil.adicionarUsuarioNaSessao(usuario);
			try {
				String urlResponse = "private/";
				if(usuario.getPerfil().equals(PerfilEnum.ALUNO)){
					urlResponse += "aluno/home.jsf";
				} else if(usuario.getPerfil().equals(PerfilEnum.PROFESSOR)){
					urlResponse += "professor/home.jsf";
				}
				EducServiceJsfUtil.obterResponse().sendRedirect(urlResponse);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			EducServiceJsfUtil.lancarMensagemDeErro("Usuário não encontrado");
		}
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
