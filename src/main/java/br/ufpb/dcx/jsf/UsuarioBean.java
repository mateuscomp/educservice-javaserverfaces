package br.ufpb.dcx.jsf;

import java.util.List;

import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

import br.ufpb.dcx.jsf.util.EducServiceJsfUtil;
import br.ufpb.dcx.model.PerfilEnum;
import br.ufpb.dcx.model.Usuario;
import br.ufpb.dcx.service.EducServiceException;
import br.ufpb.dcx.service.UsuarioService;

@RooSerializable
@RooJsfManagedBean(entity = Usuario.class, beanName = "usuarioBean")
@ViewScoped
public class UsuarioBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nome;
	private String nickName;
	private String email;

	private PerfilEnum perfil;

	@Autowired
	private UsuarioService usuarioService;

	public void cadastrar() {
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setNickName(nickName);
		usuario.setEmail(email);
		usuario.setPerfil(perfil);

		try {
			this.usuarioService.salvar(usuario);
			EducServiceJsfUtil
					.lancarMensagemInformativa("Usuário criado com sucesso! Uma senha temporária foi enviada para o endereço de e-mail informado");
		} catch (EducServiceException e) {
			EducServiceJsfUtil.lancarMensagemDeErro(e.getMessage());
		}
	}

	public void verificarNickNamePreviamenteCadastrado() {
		if (this.nickName != null && !nickName.isEmpty()) {
			Usuario usuarioComMesmoNickName = this.usuarioService
					.pesquisarUsuarioByNickNameEquals(this.nickName);

			if (usuarioComMesmoNickName != null) {
				EducServiceJsfUtil
						.lancarMensagemDeErro("Nickname já cadastrado");
			} else {
				EducServiceJsfUtil.lancarMensagemDeErro("Nickname disponível");
			}
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<PerfilEnum> getPerfis() {
		return PerfilEnum.getList();
	}

	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}
