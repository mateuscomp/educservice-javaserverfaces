package br.ufpb.dcx.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.ufpb.dcx.model.Usuario;

public class SenderMail {

	private static final long serialVersionUID = 1L;

	private static final String SMTP_HOST_NAME = "email-smtp.us-west-2.amazonaws.com";
	private static final String SMTP_USERNAME = "AKIAJ2BF42YZVI656BCQ";
	private static final String SMTP_PASSWORD = "AkOZLh2u/7U9ilUqL0/Kov6ESLHgDcj6lHKFfStyGnzp";

	String SMTP_AUTH_USER = "educs@dce.ufpb.br";

	private static final int SMTP_HOST_PORT = 25;

	private static final String LOGIN_REGEX = "#LOGIN_DO_USUARIO";
	private static final String SENHA_REGEX = "#SENHA_DO_USUARIO";

	private static final String SUBJECT_EMAIL_CADASTRO_ALUNO = "[EducService] - Cadastro de Aluno";
	private static final String SUBJECT_EMAIL_RECUPERACAO_SENHA = "[EducService] - Recuperação de Senha";

	private String corpoDoEmailCadastroUsuario = "<html>"
			+ "<h1>"
			+ "Bem vindo ao EducService!"
			+ "</h1><br/><br/><p>"
			+ "Você acabou de se cadastrar no sistema EducService com o login: '"
			+ LOGIN_REGEX
			+ "', caso contrário ignore este e-mail.</p>"
			+ "<br/>"
			+ "<p>Para se autenticar no sistema basta inserir seu endereço de e-mail no campo 'login' e a na senha preencha com: '"
			+ SENHA_REGEX + "'.</p>" + "</html>";

	private String corpoDoEmailRecuperacaoSenha = "<html>"
			+ "<p>Houve uma solicitação de recuperação de senha para "
			+ LOGIN_REGEX
			+ " e uma nova senha foi gerada. Por favor, acesse o sistema e realize o login com seu email ou nickname e a nova senha gerada"
			+ ".</p><br/><b>" + "Nova senha:</b> '"+SENHA_REGEX+"'</html>";

	public void enviarEmailComSenhaGerada(Usuario usuario)
			throws EducServiceException {

		Properties props = carregarPropriedadesSMTP();
		Session session = Session.getDefaultInstance(props);
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(SMTP_AUTH_USER));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
					usuario.getEmail()));
			msg.setSubject(SUBJECT_EMAIL_CADASTRO_ALUNO);
			msg.setContent(
					corpoDoEmailCadastroUsuario.replace(LOGIN_REGEX,
							usuario.getEmail()).replace(SENHA_REGEX,
							usuario.getSenhaDeRecuperacao()), "text/html");

			Transport transport = session.getTransport();
			transport.connect(SMTP_HOST_NAME, SMTP_USERNAME, SMTP_PASSWORD);

			transport.sendMessage(msg, msg.getAllRecipients());
		} catch (Exception ex) {
			throw new EducServiceException("Erro inesperado ao enviar e-mail!");
		}
	}

	private Properties carregarPropriedadesSMTP() {
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", SMTP_HOST_PORT);

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.required", "true");
		return props;
	}

	public void enviarEmailComRecuperacaoDeSenha(Usuario usuario) throws EducServiceException {
		Properties props = carregarPropriedadesSMTP();
		Session session = Session.getDefaultInstance(props);
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(SMTP_AUTH_USER));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
					usuario.getEmail()));
			msg.setSubject(SUBJECT_EMAIL_RECUPERACAO_SENHA);
			msg.setContent(corpoDoEmailRecuperacaoSenha.replace(LOGIN_REGEX,
							usuario.getEmail()).replace(SENHA_REGEX,
							usuario.getSenhaDeRecuperacao()), "text/html");

			Transport transport = session.getTransport();
			transport.connect(SMTP_HOST_NAME, SMTP_USERNAME, SMTP_PASSWORD);

			transport.sendMessage(msg, msg.getAllRecipients());
		} catch (Exception ex) {
			throw new EducServiceException("Erro inesperado ao enviar e-mail!");
		}
	}
}
