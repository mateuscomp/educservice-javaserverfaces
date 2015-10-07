package br.ufpb.dcx.service;

import java.io.Serializable;
import java.util.Random;

import br.ufpb.dcx.model.Usuario;

public class UsuarioServiceImpl implements UsuarioService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void salvar(Usuario usuario) throws EducServiceException {
		Usuario usuarioComMesmoEmailOuMesmoNickName = Usuario
				.findUsuarioByNickNameOrEmailEquals(usuario.getNickName()
						.toLowerCase(), usuario.getEmail().toLowerCase());
		if (usuarioComMesmoEmailOuMesmoNickName != null) {
			if (usuarioComMesmoEmailOuMesmoNickName.getNickName().equals(
					usuario.getNickName().toLowerCase())) {
				throw new UsuarioEducServiceException(
						"Já existe um usuário com o mesmo nickname já cadastrado!");
			}

			if (usuarioComMesmoEmailOuMesmoNickName.getEmail().equals(
					usuario.getEmail().toLowerCase())) {
				throw new UsuarioEducServiceException(
						"Já existe um usuário com o mesmo e-mail já cadastrado!");
			}
		}

		if (usuario.getId() == null) {
			this.gerarSenhaDeRecuperacao(usuario);
			SenderMail senderMail = new SenderMail();
			senderMail.enviarEmailComSenhaGerada(usuario);
		}

		usuario.setNickName(usuario.getNickName().toLowerCase());
		usuario.setEmail(usuario.getEmail().toLowerCase());
		usuario.setNome(usuario.getNome().toLowerCase());
		usuario.setSenha(usuario.getSenha().toLowerCase());
		usuario.setSenhaDeRecuperacao(usuario.getSenhaDeRecuperacao()
				.toLowerCase());
		this.saveUsuario(usuario);
	}

	private void gerarSenhaDeRecuperacao(Usuario usuario) {
		char[] alfabeto = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
				'L', 'M', 'N', 'P', 'Q', 'R', 'T', 'V', 'W', 'X', 'Y', 'Z',
				'2', '3', '4', '6', '7', '8', '9' };

		int qtdCaracteresDoPin = 10;
		String codigo = "";
		for (int i = 0; i < qtdCaracteresDoPin; i++) {
			Random gerador = new Random();
			int numero = gerador.nextInt(alfabeto.length - 1);
			if (numero < 0) {
				numero = 0;
			}
			codigo += alfabeto[numero];
		}
		usuario.setSenha(codigo);
		usuario.setSenhaDeRecuperacao(codigo);
	}

	@Override
	public Usuario pesquisarUsuarioByNickNameEquals(String nickName) {
		return Usuario.findUsuarioByNickNameEquals(nickName.toLowerCase());
	}

	@Override
	public Usuario pesquisarUsuarioPorEmailOrNickNameAndSenha(String login,
			String senha) {
		
		return Usuario.findUsuarioByEmailOrNickNameEqualsAndSenhaEquals(login, senha);
	}
}