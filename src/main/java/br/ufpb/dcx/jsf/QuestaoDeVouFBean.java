package br.ufpb.dcx.jsf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

import br.ufpb.dcx.jsf.util.EducServiceJsfUtil;
import br.ufpb.dcx.model.AlternativaVouF;
import br.ufpb.dcx.model.QuestaoVouF;
import br.ufpb.dcx.model.Usuario;
import br.ufpb.dcx.service.QuestaoException;
import br.ufpb.dcx.service.QuestaoService;
import br.ufpb.dcx.service.QuestaoVouFService;

@RooSerializable
@RooJsfManagedBean(entity = QuestaoVouF.class, beanName = "questaoDeVouFBean")
public class QuestaoDeVouFBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private QuestaoService questaoService;
	private Usuario professor;
	private String nomePesquisa;
	private List<QuestaoVouF> questoes;

	// QUestão verdadeiro ou falso
	@Autowired
	private QuestaoVouFService questaoVouFService;
	private QuestaoVouF questaoVerdadeiroOuFalso;
	private AlternativaVouF alternativaVerdadeiroOuFalso;
	private List<AlternativaVouF> alternativasDeQuestaoVouFRemovidas;

	public QuestaoDeVouFBean() {
		this.professor = EducServiceJsfUtil.getUsuarioDaSession();

		this.questaoVerdadeiroOuFalso = new QuestaoVouF();
		this.alternativaVerdadeiroOuFalso = new AlternativaVouF();
		this.alternativasDeQuestaoVouFRemovidas = new ArrayList<AlternativaVouF>();
	}

	public void pesquisar() {
		this.questoes = questaoVouFService.findQuestoesByProfessorEqualsAndNomeLike(professor,
						nomePesquisa);

		if (this.questoes == null || this.questoes.isEmpty()) {
			EducServiceJsfUtil
					.lancarMensagemDeAlerta("Nenhum registro encontrado!");
		}
	}

	public void editar(QuestaoVouF questao) {
			this.questaoVerdadeiroOuFalso = questao;
	}

	public void adicionarAlternativaQVF() {
		this.questaoVerdadeiroOuFalso.getAlternativas().add(
				alternativaVerdadeiroOuFalso);
		this.alternativaVerdadeiroOuFalso = new AlternativaVouF();

		EducServiceJsfUtil.lancarMensagemInformativa("Alternativa adicionada!");
	}

	public void removerAlternativaVF(AlternativaVouF alternativa) {
		this.questaoVerdadeiroOuFalso.getAlternativas().remove(alternativa);
		if (alternativa.getId() != null) {
			this.alternativasDeQuestaoVouFRemovidas.add(alternativa);
		}
		EducServiceJsfUtil.lancarMensagemInformativa("Alternativa removida!");
	}

	public void salvarQuestaoVouF() {
		this.questaoVerdadeiroOuFalso.setProfessor(professor);
		try {
			this.questaoVouFService.salvarQuestaoVouF(questaoVerdadeiroOuFalso,
					alternativasDeQuestaoVouFRemovidas);

			this.limpar();

			EducServiceJsfUtil
					.lancarMensagemInformativa("Questão de Verdadeiro ou Falso salva com sucesso!");
		} catch (QuestaoException e) {
			EducServiceJsfUtil.lancarMensagemDeErro(e.getMessage());
		}
	}
	
	public void limpar(){
		this.questaoVerdadeiroOuFalso = new QuestaoVouF();
		this.alternativaVerdadeiroOuFalso = new AlternativaVouF();
		this.alternativasDeQuestaoVouFRemovidas = new ArrayList<AlternativaVouF>();
	}

	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}

	public List<QuestaoVouF> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<QuestaoVouF> questoes) {
		this.questoes = questoes;
	}

	public QuestaoVouF getQuestaoVerdadeiroOuFalso() {
		return questaoVerdadeiroOuFalso;
	}

	public void setQuestaoVerdadeiroOuFalso(QuestaoVouF questaoVerdadeiroOuFalso) {
		this.questaoVerdadeiroOuFalso = questaoVerdadeiroOuFalso;
	}

	public Usuario getProfessor() {
		return professor;
	}

	public void setProfessor(Usuario professor) {
		this.professor = professor;
	}

	public AlternativaVouF getAlternativaVerdadeiroOuFalso() {
		return alternativaVerdadeiroOuFalso;
	}

	public void setAlternativaVerdadeiroOuFalso(
			AlternativaVouF alternativaVerdadeiroOuFalso) {
		this.alternativaVerdadeiroOuFalso = alternativaVerdadeiroOuFalso;
	}

	public List<AlternativaVouF> getAlternativasDeQuestaoVouFRemovidas() {
		return alternativasDeQuestaoVouFRemovidas;
	}

	public void setAlternativasDeQuestaoVouFRemovidas(
			List<AlternativaVouF> alternativasDeQuestaoVouFRemovidas) {
		this.alternativasDeQuestaoVouFRemovidas = alternativasDeQuestaoVouFRemovidas;
	}
}