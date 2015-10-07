package br.ufpb.dcx.jsf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpb.dcx.jsf.util.EducServiceJsfUtil;
import br.ufpb.dcx.model.Usuario;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		Usuario usuarioDaSessao;
		try {
			usuarioDaSessao = (Usuario) request.getSession().getAttribute(EducServiceJsfUtil.CHAVE_USUARIO_LOGADO_MAP_SESSION);
		} catch (Exception e) {
			usuarioDaSessao = null;
		}

		String requestURI = request.getRequestURI().toString();
		String requestUriArray[] = requestURI.split("/");

		if (usuarioDaSessao == null
				&& requestURI.contains(EducServiceJsfUtil
						.getDiretorioUsuarioLogado())) {
			response.sendRedirect("/" + requestUriArray[1]);
			return;
		}

		else if (usuarioDaSessao != null && requestURI.contains(EducServiceJsfUtil.getUrlLoginPage())) {
			response.sendRedirect("/" + requestUriArray[1] + "/" + EducServiceJsfUtil.getUrlHomePageUsuarioLogado());
			return;
		}

		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
