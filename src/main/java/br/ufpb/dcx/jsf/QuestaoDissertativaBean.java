package br.ufpb.dcx.jsf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

import br.ufpb.dcx.jsf.util.EducServiceJsfUtil;
import br.ufpb.dcx.model.QuestaoDissertativa;
import br.ufpb.dcx.model.Usuario;
import br.ufpb.dcx.service.QuestaoDissertativaService;

@RooSerializable
@RooJsfManagedBean(entity = QuestaoDissertativa.class, beanName = "questaoDissertativaBean")
public class QuestaoDissertativaBean {

	private static final long serialVersionUID = 1L;

	@Autowired
	private QuestaoDissertativaService questaoDissertativaService;

	private Usuario professor;
	private String nomePesquisa;
	private List<QuestaoDissertativa> questoesDissertativas;

	private QuestaoDissertativa questaoDissertativa;

	public QuestaoDissertativaBean() {
		this.professor = EducServiceJsfUtil.getUsuarioDaSession();
		this.questaoDissertativa = new QuestaoDissertativa();
	}

	public void pesquisar() {
		this.questoesDissertativas = this.questaoDissertativaService.pesquisarQuestoesDissertativasPorProfessorAndNome(professor, nomePesquisa);

		if (this.questoesDissertativas == null || this.questoesDissertativas.isEmpty()) {
			EducServiceJsfUtil
					.lancarMensagemDeAlerta("Nenhum registro encontrado!");
		}
	}

	public void editar(QuestaoDissertativa questao) {
		this.questaoDissertativa = questao;
	}

	public void salvarQuestaoDissertativa() {
		this.questaoDissertativa.setProfessor(professor);
		this.questaoDissertativaService.salvarQuestaoDissertativa(questaoDissertativa);

		this.questaoDissertativa = new QuestaoDissertativa();

		EducServiceJsfUtil
				.lancarMensagemInformativa("Questão Dissertativa salva com sucesso!");
	}
	
	public void limparFormCadastro(){
		this.questaoDissertativa = new QuestaoDissertativa();
	}

	public Usuario getProfessor() {
		return professor;
	}

	public void setProfessor(Usuario professor) {
		this.professor = professor;
	}

	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}

	public List<QuestaoDissertativa> getQuestoesDissertativas() {
		return questoesDissertativas;
	}

	public void setQuestoesDissertativas(
			List<QuestaoDissertativa> questoesDissertativas) {
		this.questoesDissertativas = questoesDissertativas;
	}

	public QuestaoDissertativa getQuestaoDissertativa() {
		return questaoDissertativa;
	}

	public void setQuestaoDissertativa(QuestaoDissertativa questaoDissertativa) {
		this.questaoDissertativa = questaoDissertativa;
	}
}