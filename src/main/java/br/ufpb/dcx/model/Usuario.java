package br.ufpb.dcx.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     */
	@NotNull
	private String nome;

	/**
     */
	@NotNull
	private String nickName;

	/**
     */
	@NotNull
	private String email;

	/**
     */
	@NotNull
	private String senha;

	/**
     */
	private String senhaDeRecuperacao;

	/**
     */
	private boolean isAtivo;

	/**
     */
	@Enumerated(EnumType.STRING)
	@NotNull
	private PerfilEnum perfil;

	public static Usuario findUsuarioByNickNameOrEmailEquals(String nickName,
			String email) {
		EntityManager em = Usuario.entityManager();
		TypedQuery<Usuario> q = em
				.createQuery(
						"SELECT u FROM Usuario AS u WHERE u.nickName = :nickName OR u.email = :email",
						Usuario.class);
		q.setParameter("nickName", nickName);
		q.setParameter("email", email);

		List<Usuario> usuarios = q.getResultList();

		if (usuarios != null && usuarios.size() > 0) {
			return usuarios.get(0);
		} else {
			return null;
		}
	}

	public static Usuario findUsuarioByNickNameEquals(String nickName) {
		EntityManager em = Usuario.entityManager();
		TypedQuery<Usuario> q = em.createQuery(
				"SELECT u FROM Usuario AS u WHERE u.nickName = :nickName",
				Usuario.class);
		q.setParameter("nickName", nickName);

		List<Usuario> usuarios = q.getResultList();

		if (usuarios != null && usuarios.size() > 0) {
			return usuarios.get(0);
		} else {
			return null;
		}
	}

	public static Usuario findUsuarioByEmailOrNickNameEqualsAndSenhaEquals(
			String emailOrNickName, String senha) {

		EntityManager em = Usuario.entityManager();
		TypedQuery<Usuario> q = em
				.createQuery(
						"SELECT u FROM Usuario AS u WHERE (u.nickName = :nickName OR u.email = :email) AND u.senha = :senha",
						Usuario.class);
		q.setParameter("nickName", emailOrNickName);
		q.setParameter("email", emailOrNickName);
		q.setParameter("senha", senha);

		List<Usuario> usuarios = q.getResultList();

		if (usuarios != null && usuarios.size() > 0) {
			return usuarios.get(0);
		} else {
			return null;
		}

	}
}
