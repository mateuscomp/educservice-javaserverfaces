package br.ufpb.dcx.jsf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

import br.ufpb.dcx.jsf.util.EducServiceJsfUtil;
import br.ufpb.dcx.model.AlternativaDeQuestaoDeMultiplaEscolha;
import br.ufpb.dcx.model.QuestaoDeMultiplaEscolha;
import br.ufpb.dcx.model.Usuario;
import br.ufpb.dcx.service.QuestaoDeMultiplaEscolhaService;
import br.ufpb.dcx.service.QuestaoException;
import br.ufpb.dcx.service.QuestaoService;

@RooSerializable
@RooJsfManagedBean(entity = QuestaoDeMultiplaEscolha.class, beanName = "questaoDeMultiplaEscolhaBean")
public class QuestaoDeMultiplaEscolhaBean {

	private static final long serialVersionUID = 1L;

	@Autowired
	private QuestaoDeMultiplaEscolhaService questaoDeMultiplaEscolhaService;

	@Autowired
	private QuestaoService questaoService;
	private Usuario professor;
	private String nomePesquisa;
	private List<QuestaoDeMultiplaEscolha> questoes;

	// Questão de múltipla escolha
	private QuestaoDeMultiplaEscolha questaoDeMultiplaEscolha;
	private AlternativaDeQuestaoDeMultiplaEscolha alternativaDeQuestaoDeMultiplaEscolha;
	private List<AlternativaDeQuestaoDeMultiplaEscolha> alternativasDeQuestaoDeMultiplaEscolhaRemovidas;

	public QuestaoDeMultiplaEscolhaBean() {
		this.professor = EducServiceJsfUtil.getUsuarioDaSession();

		this.questaoDeMultiplaEscolha = new QuestaoDeMultiplaEscolha();
		this.alternativaDeQuestaoDeMultiplaEscolha = new AlternativaDeQuestaoDeMultiplaEscolha();
		this.alternativasDeQuestaoDeMultiplaEscolhaRemovidas = new ArrayList<AlternativaDeQuestaoDeMultiplaEscolha>();

	}

	public void pesquisar() {
		this.questoes = questaoDeMultiplaEscolhaService
				.pesquisarQuestoesDeMultiplaEscolhaPorProfessorAndNome(
						professor, nomePesquisa);

		if (this.questoes == null || this.questoes.isEmpty()) {
			EducServiceJsfUtil
					.lancarMensagemDeAlerta("Nenhum registro encontrado!");
		}
	}

	public void editar(QuestaoDeMultiplaEscolha questao) {
		this.questaoDeMultiplaEscolha = questao;
	}

	public void adicionarAlternativaQME() {
		this.questaoDeMultiplaEscolha.getAlternativas().add(
				alternativaDeQuestaoDeMultiplaEscolha);
		this.alternativaDeQuestaoDeMultiplaEscolha = new AlternativaDeQuestaoDeMultiplaEscolha();

		EducServiceJsfUtil.lancarMensagemInformativa("Alternativa adicionada!");
	}

	public void removerAlternativaMultiplaEscolha(
			AlternativaDeQuestaoDeMultiplaEscolha alternativa) {

		this.questaoDeMultiplaEscolha.getAlternativas().remove(alternativa);
		if (alternativa.getId() != null) {
			this.alternativasDeQuestaoDeMultiplaEscolhaRemovidas
					.add(alternativa);
		}
		EducServiceJsfUtil.lancarMensagemInformativa("Alternativa removida!");
	}

	public void salvarQuestaoDeMultiplaEscolha() {
		this.questaoDeMultiplaEscolha.setProfessor(professor);
		try {
			this.questaoDeMultiplaEscolhaService
					.salvarQuestaoDeMultiplaEscolha(
							this.questaoDeMultiplaEscolha,
							alternativasDeQuestaoDeMultiplaEscolhaRemovidas);

			this.limparCadastro();
			
			EducServiceJsfUtil
					.lancarMensagemInformativa("Questão de Múltipla Escolha salva com sucesso!");
		} catch (QuestaoException e) {
			EducServiceJsfUtil.lancarMensagemDeErro(e.getMessage());
		}
	}

	public void limparCadastro() {
		this.questaoDeMultiplaEscolha = new QuestaoDeMultiplaEscolha();
		this.alternativaDeQuestaoDeMultiplaEscolha = new AlternativaDeQuestaoDeMultiplaEscolha();
		this.alternativasDeQuestaoDeMultiplaEscolhaRemovidas = new ArrayList<AlternativaDeQuestaoDeMultiplaEscolha>();
	}

	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}

	public List<QuestaoDeMultiplaEscolha> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<QuestaoDeMultiplaEscolha> questoes) {
		this.questoes = questoes;
	}

	public AlternativaDeQuestaoDeMultiplaEscolha getAlternativaDeQuestaoDeMultiplaEscolha() {
		return alternativaDeQuestaoDeMultiplaEscolha;
	}

	public void setAlternativaDeQuestaoDeMultiplaEscolha(
			AlternativaDeQuestaoDeMultiplaEscolha alternativaDeQuestaoDeMultiplaEscolha) {
		this.alternativaDeQuestaoDeMultiplaEscolha = alternativaDeQuestaoDeMultiplaEscolha;
	}

	public Usuario getProfessor() {
		return professor;
	}

	public void setProfessor(Usuario professor) {
		this.professor = professor;
	}

	public QuestaoDeMultiplaEscolha getQuestaoDeMultiplaEscolha() {
		return questaoDeMultiplaEscolha;
	}

	public void setQuestaoDeMultiplaEscolha(
			QuestaoDeMultiplaEscolha questaoDeMultiplaEscolha) {
		this.questaoDeMultiplaEscolha = questaoDeMultiplaEscolha;
	}

	public List<AlternativaDeQuestaoDeMultiplaEscolha> getAlternativasDeQuestaoDeMultiplaEscolhaRemovidas() {
		return alternativasDeQuestaoDeMultiplaEscolhaRemovidas;
	}

	public void setAlternativasDeQuestaoDeMultiplaEscolhaRemovidas(
			List<AlternativaDeQuestaoDeMultiplaEscolha> alternativasDeQuestaoDeMultiplaEscolhaRemovidas) {
		this.alternativasDeQuestaoDeMultiplaEscolhaRemovidas = alternativasDeQuestaoDeMultiplaEscolhaRemovidas;
	}
}