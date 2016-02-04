package br.ufpb.dcx.service;

import org.springframework.roo.addon.layers.service.RooService;

import br.ufpb.dcx.model.Usuario;

@RooService(domainTypes = { br.ufpb.dcx.model.Usuario.class })
public interface UsuarioService {

	void salvar(Usuario usuario) throws EducServiceException;

	Usuario pesquisarUsuarioByNickNameEquals(String nickName);

	Usuario pesquisarUsuarioPorEmailOrNickNameAndSenha(String login, String senha);

	Usuario recuperarSenha(String nickname, String email) throws EducServiceException;

}
