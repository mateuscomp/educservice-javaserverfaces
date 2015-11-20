package br.ufpb.dcx.jsf.util;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufpb.dcx.model.Usuario;

public class EducServiceJsfUtil {

	private static final String URL_LOGIN_PAGE = "pages/main.jsf";
	private static final String URL_HOME_PAGE_USUARIO_LOGADO = "pages/private/home.jsf";
	public static final String CHAVE_USUARIO_LOGADO_MAP_SESSION = "USUARIOLOGADO";
	private static final String DIRETORIO_USUARIO_LOGADO = "private/";

	public static void lancarMensagemInformativa(String msg) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
	}
	
	public static void lancarMensagemDeAlerta(String msg) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, msg, null));
	}

	public static void lancarMensagemDeErro(String msg) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
	}

	public static void adicionarUsuarioNaSessao(Usuario usuario) {
		obterSession().setAttribute(CHAVE_USUARIO_LOGADO_MAP_SESSION, usuario);

	}

	public static Usuario getUsuarioDaSession() {
		return (Usuario) obterSession().getAttribute(
				CHAVE_USUARIO_LOGADO_MAP_SESSION);
	}

	public static HttpSession obterSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}

	public static HttpServletRequest obterRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getCurrentInstance().getExternalContext().getRequest();
	}

	public static HttpServletResponse obterResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance()
				.getCurrentInstance().getExternalContext().getResponse();
	}

	public static String getUrlLoginPage() {
		return URL_LOGIN_PAGE;
	}

	public static String getUrlHomePageUsuarioLogado() {
		return URL_HOME_PAGE_USUARIO_LOGADO;
	}

	public static CharSequence getDiretorioUsuarioLogado() {
		return DIRETORIO_USUARIO_LOGADO;
	}

	public static void limparCicloDeVidaDaArvore() {
		UIComponent component = FacesContext.getCurrentInstance().getViewRoot();
		cleanSubmittedValues(component);
	}

	private static void cleanSubmittedValues(UIComponent component) {
		if (component instanceof EditableValueHolder) {
			EditableValueHolder evh = (EditableValueHolder) component;
			evh.setSubmittedValue(null);
			evh.setValue(null);
			evh.setLocalValueSet(false);
			evh.setValid(true);
		}
		if (component.getChildCount() > 0) {
			for (UIComponent child : component.getChildren()) {
				cleanSubmittedValues(child);
			}
		}
	}

	public static void removerUsuarioDaSessao() {
		HttpSession sessao = EducServiceJsfUtil.obterSession();
		sessao.setAttribute(EducServiceJsfUtil.CHAVE_USUARIO_LOGADO_MAP_SESSION, null);


		String uri = EducServiceJsfUtil.obterRequest().getRequestURI()
				.toString();

		try {
			EducServiceJsfUtil.obterResponse().sendRedirect(uri);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
