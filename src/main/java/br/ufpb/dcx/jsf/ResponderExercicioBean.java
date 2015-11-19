package br.ufpb.dcx.jsf;

import java.io.Serializable;
import java.util.List;

import javax.faces.component.html.HtmlPanelGrid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

import br.ufpb.dcx.jsf.util.EducServiceJsfUtil;
import br.ufpb.dcx.model.AlternativaDeQuestaoDeMultiplaEscolha;
import br.ufpb.dcx.model.Exercicio;
import br.ufpb.dcx.model.Questao;
import br.ufpb.dcx.model.QuestaoDeMultiplaEscolha;
import br.ufpb.dcx.model.QuestaoDissertativa;
import br.ufpb.dcx.model.QuestaoVouF;
import br.ufpb.dcx.model.RespostaDeAlternativaVouF;
import br.ufpb.dcx.model.RespostaDeExercicio;
import br.ufpb.dcx.model.RespostaDeQuestaoDeMultiplaEscolha;
import br.ufpb.dcx.model.RespostaDeQuestaoDeUmExercicio;
import br.ufpb.dcx.model.RespostaDeQuestaoDeVouF;
import br.ufpb.dcx.model.RespostaDeQuestaoDissertativa;
import br.ufpb.dcx.model.Usuario;
import br.ufpb.dcx.service.ExercicioService;
import br.ufpb.dcx.service.QuestaoDeMultiplaEscolhaService;
import br.ufpb.dcx.service.QuestaoDissertativaService;
import br.ufpb.dcx.service.QuestaoVouFService;
import br.ufpb.dcx.service.RespostaDeExercicioService;
import br.ufpb.dcx.service.RespostaDeQuestaoDeUmExercicioService;

@RooSerializable
@RooJsfManagedBean(entity = RespostaDeExercicio.class, beanName = "responderExercicioBean")
public class ResponderExercicioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ExercicioService exercicioService;

	@Autowired
	private QuestaoDeMultiplaEscolhaService questaoDeMultiplaEscolhaService;

	@Autowired
	private QuestaoDissertativaService questaoDissertativaService;

	@Autowired
	private QuestaoVouFService questaoVouFService;
	
	@Autowired
	private RespostaDeExercicioService respostaDeExercicioService;
	
	@Autowired
	private RespostaDeQuestaoDeUmExercicioService respostaDeQuestaoDeUmExercicioService;

	private String apelidoDoProfessorPesquisa;
	private String emailDoProfessorPesquisa;

	private List<Exercicio> exercicios;
	private Exercicio exercicio;
	int indiceDeQuestao = 0;

	private Questao questao;
	private QuestaoDissertativa questaoDissertativa;
	private QuestaoDeMultiplaEscolha questaoDeMultiplaEscolha;
	private QuestaoVouF questaoVouF;

	private AlternativaDeQuestaoDeMultiplaEscolha alternativaDeQuestaoDeMultiplaEscolha;
	private String solucaoDeQuestaoDissertativa;

	private RespostaDeExercicio respostaDeExercicio;
	private RespostaDeQuestaoDeUmExercicio respostaDeQuestao;
	private List<RespostaDeQuestaoDeUmExercicio> respostasDeQuestoes;

	private RespostaDeQuestaoDeMultiplaEscolha respostaDeQuestaoDeMultiplaEscolha;
	private RespostaDeQuestaoDissertativa respostaDeQuestaoDissertativa;
	private RespostaDeQuestaoDeVouF respostaDeQuestaoDeVouF;

	private List<RespostaDeAlternativaVouF> respostasAlternativasVouFSelecionadas;

	public ResponderExercicioBean() {
		this.questao = new Questao();
	}
	
	public void testarOneMenu(){
		System.out.println("Mudou alguma coisa");
	}

	public void pesquisar() {
		this.exercicios = exercicioService
				.pesquisarExerciciosPorNomeOuEmailDeProfessor(
						apelidoDoProfessorPesquisa, emailDoProfessorPesquisa);
		if (this.exercicios == null || this.exercicios.isEmpty()) {
			EducServiceJsfUtil
					.lancarMensagemDeAlerta("Nenhum exercício encontrado!");
		}
	}
	
	public void responderQuestaoDeMultiplaEscolha(){
		this.respostaDeQuestao = new RespostaDeQuestaoDeUmExercicio();
		this.respostaDeQuestao.setRespostaDeExercicio(respostaDeExercicio);
		this.respostaDeQuestao.setQuestao(questao);
		
		this.respostaDeQuestaoDeUmExercicioService.saveRespostaDeQuestaoDeUmExercicio(respostaDeQuestao);
		
		for(int i = 0; i < questaoDeMultiplaEscolha.getAlternativas().size(); i ++){
			if(questaoDeMultiplaEscolha.getAlternativas().get(i).getDescricao().equals(alternativaDeQuestaoDeMultiplaEscolha.getDescricao())){
				respostaDeQuestaoDeMultiplaEscolha.setIndiceDaAlternativaCorreta(i);
				break;
			}
		}
	}

	public void responder(Exercicio exercicio) {
		this.exercicio = exercicio;
		this.indiceDeQuestao = 0;

		questao = exercicio.getQuestoes().get(indiceDeQuestao);
		this.selecionarQuestao(questao);

		Usuario aluno = EducServiceJsfUtil.getUsuarioDaSession();
		this.respostaDeExercicio = new RespostaDeExercicio();
		this.respostaDeExercicio.setAluno(aluno);
		this.respostaDeExercicio.setExercicio(exercicio);
		this.respostaDeExercicioService.saveRespostaDeExercicio(this.respostaDeExercicio);

	}

	private void selecionarQuestao(Questao questao) {
		QuestaoDeMultiplaEscolha questaoMEEdicao = this.questaoDeMultiplaEscolhaService
				.pesquisarPorIdDeQuestao(questao);
		if (questaoMEEdicao != null) {
			this.questaoDeMultiplaEscolha = questaoMEEdicao;

			this.questaoDissertativa = null;
			this.respostaDeQuestaoDissertativa = null;

			this.questaoVouF = null;
			this.respostaDeQuestaoDeVouF = null;

			this.respostaDeQuestaoDeMultiplaEscolha = new RespostaDeQuestaoDeMultiplaEscolha();
			this.respostaDeQuestaoDeMultiplaEscolha.setRespostaDeQuestaoDeUmExercicio(this.respostaDeQuestao);

			return;
		}

		QuestaoDissertativa questaoDEdicao = this.questaoDissertativaService
				.pesquisarPorIdDeQuestao(questao);
		if (questaoDEdicao != null) {
			this.questaoDissertativa = questaoDEdicao;

			this.questaoDeMultiplaEscolha = null;
			this.respostaDeQuestaoDeMultiplaEscolha = null;

			this.questaoVouF = null;
			this.respostaDeQuestaoDeVouF = null;

			this.respostaDeQuestaoDissertativa = new RespostaDeQuestaoDissertativa();
			this.respostaDeQuestaoDissertativa
					.setRespostaDeQuestaoDeUmExercicio(this.respostaDeQuestao);

			return;
		}

		QuestaoVouF questaoVFEdicao = this.questaoVouFService
				.pesquisarPorIdDeQuestao(questao);
		if (questaoVFEdicao != null) {
			this.questaoVouF = questaoVFEdicao;

			this.questaoDeMultiplaEscolha = null;
			this.respostaDeQuestaoDeMultiplaEscolha = null;

			this.questaoDissertativa = null;
			this.respostaDeQuestaoDissertativa = null;

			this.respostaDeQuestaoDeVouF = new RespostaDeQuestaoDeVouF();
			this.respostaDeQuestaoDeVouF
					.setRespostaDeQuestaoDeUmExercicio(this.respostaDeQuestao);
		}
	}

	public String getApelidoDoProfessorPesquisa() {
		return apelidoDoProfessorPesquisa;
	}

	public void setApelidoDoProfessorPesquisa(String apelidoDoProfessorPesquisa) {
		this.apelidoDoProfessorPesquisa = apelidoDoProfessorPesquisa;
	}

	public List<Exercicio> getExercicios() {
		return exercicios;
	}

	public void setExercicios(List<Exercicio> exercicios) {
		this.exercicios = exercicios;
	}

	public String getEmailDoProfessorPesquisa() {
		return emailDoProfessorPesquisa;
	}

	public void setEmailDoProfessorPesquisa(String emailDoProfessorPesquisa) {
		this.emailDoProfessorPesquisa = emailDoProfessorPesquisa;
	}

	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public boolean getRenderizarQuestaoDeMultiplaEscolha() {
		return this.questaoDeMultiplaEscolha != null;
	}

	public boolean getRenderizarQuestaoDissertativa() {
		return this.questaoDissertativa != null;
	}

	public boolean getRenderizarQuestaoDeVouF() {
		return this.questaoVouF != null;
	}

	public QuestaoDissertativa getQuestaoDissertativa() {
		return questaoDissertativa;
	}

	public void setQuestaoDissertativa(QuestaoDissertativa questaoDissertativa) {
		this.questaoDissertativa = questaoDissertativa;
	}

	public QuestaoDeMultiplaEscolha getQuestaoDeMultiplaEscolha() {
		return questaoDeMultiplaEscolha;
	}

	public void setQuestaoDeMultiplaEscolha(
			QuestaoDeMultiplaEscolha questaoDeMultiplaEscolha) {
		this.questaoDeMultiplaEscolha = questaoDeMultiplaEscolha;
	}

	public QuestaoVouF getQuestaoVouF() {
		return questaoVouF;
	}

	public void setQuestaoVouF(QuestaoVouF questaoVouF) {
		this.questaoVouF = questaoVouF;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public AlternativaDeQuestaoDeMultiplaEscolha getAlternativaDeQuestaoDeMultiplaEscolha() {
		return alternativaDeQuestaoDeMultiplaEscolha;
	}

	public void setAlternativaDeQuestaoDeMultiplaEscolha(
			AlternativaDeQuestaoDeMultiplaEscolha alternativaDeQuestaoDeMultiplaEscolha) {
		this.alternativaDeQuestaoDeMultiplaEscolha = alternativaDeQuestaoDeMultiplaEscolha;
	}

	public String getSolucaoDeQuestaoDissertativa() {
		return solucaoDeQuestaoDissertativa;
	}

	public void setSolucaoDeQuestaoDissertativa(
			String solucaoDeQuestaoDissertativa) {
		this.solucaoDeQuestaoDissertativa = solucaoDeQuestaoDissertativa;
	}

	public int getIndiceDeQuestao() {
		return indiceDeQuestao;
	}

	public void setIndiceDeQuestao(int indiceDeQuestao) {
		this.indiceDeQuestao = indiceDeQuestao;
	}

	public RespostaDeExercicio getRespostaDeExercicio() {
		return respostaDeExercicio;
	}

	public void setRespostaDeExercicio(RespostaDeExercicio respostaDeExercicio) {
		this.respostaDeExercicio = respostaDeExercicio;
	}

	public RespostaDeQuestaoDeUmExercicio getRespostaDeQuestao() {
		return respostaDeQuestao;
	}

	public void setRespostaDeQuestao(
			RespostaDeQuestaoDeUmExercicio respostaDeQuestao) {
		this.respostaDeQuestao = respostaDeQuestao;
	}

	public List<RespostaDeQuestaoDeUmExercicio> getRespostasDeQuestoes() {
		return respostasDeQuestoes;
	}

	public void setRespostasDeQuestoes(
			List<RespostaDeQuestaoDeUmExercicio> respostasDeQuestoes) {
		this.respostasDeQuestoes = respostasDeQuestoes;
	}

	public RespostaDeQuestaoDeMultiplaEscolha getRespostaDeQuestaoDeMultiplaEscolha() {
		return respostaDeQuestaoDeMultiplaEscolha;
	}

	public void setRespostaDeQuestaoDeMultiplaEscolha(
			RespostaDeQuestaoDeMultiplaEscolha respostaDeQuestaoDeMultiplaEscolha) {
		this.respostaDeQuestaoDeMultiplaEscolha = respostaDeQuestaoDeMultiplaEscolha;
	}

	public RespostaDeQuestaoDissertativa getRespostaDeQuestaoDissertativa() {
		return respostaDeQuestaoDissertativa;
	}

	public void setRespostaDeQuestaoDissertativa(
			RespostaDeQuestaoDissertativa respostaDeQuestaoDissertativa) {
		this.respostaDeQuestaoDissertativa = respostaDeQuestaoDissertativa;
	}

	public RespostaDeQuestaoDeVouF getRespostaDeQuestaoDeVouF() {
		return respostaDeQuestaoDeVouF;
	}

	public void setRespostaDeQuestaoDeVouF(
			RespostaDeQuestaoDeVouF respostaDeQuestaoDeVouF) {
		this.respostaDeQuestaoDeVouF = respostaDeQuestaoDeVouF;
	}

	public HtmlPanelGrid populateCreatePanel() {
		return null;
	}

	public HtmlPanelGrid populateEditPanel() {
		return null;
	}

	public HtmlPanelGrid populateViewPanel() {
		return null;
	}

	public List<RespostaDeAlternativaVouF> getRespostasAlternativasVouFSelecionadas() {
		return respostasAlternativasVouFSelecionadas;
	}

	public void setRespostasAlternativasVouFSelecionadas(
			List<RespostaDeAlternativaVouF> respostasAlternativasVouFSelecionadas) {
		this.respostasAlternativasVouFSelecionadas = respostasAlternativasVouFSelecionadas;
	}

}
