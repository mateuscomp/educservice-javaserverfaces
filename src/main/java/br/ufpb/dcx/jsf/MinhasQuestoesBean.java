package br.ufpb.dcx.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

import br.ufpb.dcx.jsf.util.EducServiceJsfUtil;
import br.ufpb.dcx.model.AlternativaDeQuestaoDeMultiplaEscolha;
import br.ufpb.dcx.model.AlternativaVouF;
import br.ufpb.dcx.model.Questao;
import br.ufpb.dcx.model.QuestaoDeMultiplaEscolha;
import br.ufpb.dcx.model.QuestaoDissertativa;
import br.ufpb.dcx.model.QuestaoVouF;
import br.ufpb.dcx.model.Usuario;
import br.ufpb.dcx.service.QuestaoDeMultiplaEscolhaService;
import br.ufpb.dcx.service.QuestaoDissertativaService;
import br.ufpb.dcx.service.QuestaoException;
import br.ufpb.dcx.service.QuestaoService;
import br.ufpb.dcx.service.QuestaoVouFService;

@RooSerializable
@RooJsfManagedBean(entity = Questao.class, beanName = "minhasQuestoesBean")
@ViewScoped
public class MinhasQuestoesBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private QuestaoService questaoService;
	private Usuario professor;
	private Questao questaoSelecionada;
	private String nomePesquisa;
	private List<Questao> questoes;

	// Questão de múltipla escolha
	@Autowired
	private QuestaoDeMultiplaEscolhaService questaoDeMultiplaEscolhaService;
	private QuestaoDeMultiplaEscolha questaoDeMultiplaEscolha;
	private AlternativaDeQuestaoDeMultiplaEscolha alternativaDeQuestaoDeMultiplaEscolha;
	private List<AlternativaDeQuestaoDeMultiplaEscolha> alternativasDeQuestaoDeMultiplaEscolhaRemovidas;

	// Questão dissertativa
	@Autowired
	private QuestaoDissertativaService questaoDissertativaService;
	private QuestaoDissertativa questaoDissertativa;

	// QUestão verdadeiro ou falso
	@Autowired
	private QuestaoVouFService questaoVouFService;
	private QuestaoVouF questaoVerdadeiroOuFalso;
	private AlternativaVouF alternativaVerdadeiroOuFalso;
	private List<AlternativaVouF> alternativasDeQuestaoVouFRemovidas;

	private Map<Class, Integer> mapQuestaoTab;
	private Integer indexTabView;

	public MinhasQuestoesBean() {
		this.professor = EducServiceJsfUtil.getUsuarioDaSession();

		this.questaoDeMultiplaEscolha = new QuestaoDeMultiplaEscolha();
		this.alternativaDeQuestaoDeMultiplaEscolha = new AlternativaDeQuestaoDeMultiplaEscolha();
		this.alternativasDeQuestaoDeMultiplaEscolhaRemovidas = new ArrayList<AlternativaDeQuestaoDeMultiplaEscolha>();

		this.questaoDissertativa = new QuestaoDissertativa();

		this.questaoVerdadeiroOuFalso = new QuestaoVouF();
		this.alternativaVerdadeiroOuFalso = new AlternativaVouF();
		this.alternativasDeQuestaoVouFRemovidas = new ArrayList<AlternativaVouF>();

		this.mapQuestaoTab = new HashMap<Class, Integer>();
		this.mapQuestaoTab.put(QuestaoDeMultiplaEscolha.class, 0);
		this.mapQuestaoTab.put(QuestaoDissertativa.class, 1);
		this.mapQuestaoTab.put(QuestaoVouF.class, 2);
	}

	public void pesquisar() {
		this.questoes = questaoService
				.findQuestoesByProfessorEqualsAndNomeLike(professor,
						nomePesquisa);

		if (this.questoes == null || this.questoes.isEmpty()) {
			EducServiceJsfUtil
					.lancarMensagemDeAlerta("Nenhum registro encontrado!");
		}
	}

	public void editar(Questao questao) {
		if (questao instanceof QuestaoDeMultiplaEscolha) {
			this.questaoDeMultiplaEscolha = this.questaoDeMultiplaEscolhaService
					.pesquisarPorIdDeQuestao(questao);
			indexTabView = mapQuestaoTab.get(questaoDeMultiplaEscolha
					.getClass());

			this.questaoDissertativa = new QuestaoDissertativa();
			this.questaoVerdadeiroOuFalso = new QuestaoVouF();

		} else if (questao instanceof QuestaoDissertativa) {
			this.questaoDissertativa = this.questaoDissertativaService
					.pesquisarPorIdDeQuestao(questao);
			indexTabView = mapQuestaoTab.get(questaoDissertativa.getClass());

			this.questaoDeMultiplaEscolha = new QuestaoDeMultiplaEscolha();
			this.questaoVerdadeiroOuFalso = new QuestaoVouF();
		}

		else if (questao instanceof QuestaoVouF) {
			this.questaoVerdadeiroOuFalso = this.questaoVouFService
					.pesquisarPorIdDeQuestao(questao);
			indexTabView = mapQuestaoTab.get(questaoVerdadeiroOuFalso.getClass());

			this.questaoDeMultiplaEscolha = new QuestaoDeMultiplaEscolha();
			this.questaoDissertativa = new QuestaoDissertativa();
		} else {
			EducServiceJsfUtil.lancarMensagemDeErro("Questão não encontrada!");
		}
	}

	public void adicionarAlternativaQME() {
		this.questaoDeMultiplaEscolha.getAlternativas().add(
				alternativaDeQuestaoDeMultiplaEscolha);
		this.alternativaDeQuestaoDeMultiplaEscolha = new AlternativaDeQuestaoDeMultiplaEscolha();

		EducServiceJsfUtil.lancarMensagemInformativa("Alternativa adicionada!");
	}

	public void adicionarAlternativaQVF() {
		this.questaoVerdadeiroOuFalso.getAlternativas().add(
				alternativaVerdadeiroOuFalso);
		this.alternativaVerdadeiroOuFalso = new AlternativaVouF();

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

	public void removerAlternativaVF(AlternativaVouF alternativa) {
		this.questaoVerdadeiroOuFalso.getAlternativas().remove(alternativa);
		if (alternativa.getId() != null) {
			this.alternativasDeQuestaoVouFRemovidas.add(alternativa);
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

			this.questaoDeMultiplaEscolha = new QuestaoDeMultiplaEscolha();

			EducServiceJsfUtil
					.lancarMensagemInformativa("Questão de Múltipla Escolha salva com sucesso!");
		} catch (QuestaoException e) {
			EducServiceJsfUtil.lancarMensagemDeErro(e.getMessage());
		}
	}

	public void salvarQuestaoDissertativa() {
		this.questaoDissertativa.setProfessor(professor);
		this.questaoDissertativaService
				.salvarQuestaoDissertativa(questaoDissertativa);

		this.questaoDissertativa = new QuestaoDissertativa();

		EducServiceJsfUtil
				.lancarMensagemInformativa("Questão Dissertativa salva com sucesso!");
	}

	public void salvarQuestaoVouF() {
		this.questaoVerdadeiroOuFalso.setProfessor(professor);
		try {
			this.questaoVouFService.salvarQuestaoVouF(questaoVerdadeiroOuFalso,
					alternativasDeQuestaoVouFRemovidas);

			this.questaoVerdadeiroOuFalso = new QuestaoVouF();

			EducServiceJsfUtil
					.lancarMensagemInformativa("Questão de Verdadeiro ou Falso salva com sucesso!");
		} catch (QuestaoException e) {
			EducServiceJsfUtil.lancarMensagemDeErro(e.getMessage());
		}
	}

	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public Questao getQuestaoSelecionada() {
		return questaoSelecionada;
	}

	public void setQuestaoSelecionada(Questao questaoSelecionada) {
		this.questaoSelecionada = questaoSelecionada;
	}

	public AlternativaDeQuestaoDeMultiplaEscolha getAlternativaDeQuestaoDeMultiplaEscolha() {
		return alternativaDeQuestaoDeMultiplaEscolha;
	}

	public void setAlternativaDeQuestaoDeMultiplaEscolha(
			AlternativaDeQuestaoDeMultiplaEscolha alternativaDeQuestaoDeMultiplaEscolha) {
		this.alternativaDeQuestaoDeMultiplaEscolha = alternativaDeQuestaoDeMultiplaEscolha;
	}

	public QuestaoDissertativa getQuestaoDissertativa() {
		return questaoDissertativa;
	}

	public void setQuestaoDissertativa(QuestaoDissertativa questaoDissertativa) {
		this.questaoDissertativa = questaoDissertativa;
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

	public Integer getIndexTabView() {
		return indexTabView;
	}

	public void setIndexTabView(Integer indexTabView) {
		this.indexTabView = indexTabView;
	}

	public Questao getQuestao() {
		return this.questaoSelecionada;
	}

	public String displayCreateDialog() {
		return "questao";
	}
}