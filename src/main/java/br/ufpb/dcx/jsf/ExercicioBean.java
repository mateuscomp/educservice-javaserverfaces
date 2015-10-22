package br.ufpb.dcx.jsf;

import java.util.List;

import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

import br.ufpb.dcx.jsf.util.EducServiceJsfUtil;
import br.ufpb.dcx.model.Exercicio;
import br.ufpb.dcx.model.Questao;
import br.ufpb.dcx.model.Usuario;
import br.ufpb.dcx.service.EducServiceException;
import br.ufpb.dcx.service.ExercicioService;
import br.ufpb.dcx.service.QuestaoService;

@RooSerializable
@RooJsfManagedBean(entity = Usuario.class, beanName = "exercicioBean")
@ViewScoped
public class ExercicioBean {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ExercicioService exercicioService;
	
	@Autowired
	private QuestaoService questaoService;

	private Exercicio exercicio;
	private List<Exercicio> exercicios;

	private String nomeQuestaoPesquisa;
	private List<Questao> questoesDePesquisa;

	public ExercicioBean() {
		this.exercicio = new Exercicio();
	}

	public void salvar() {
		try {
			this.exercicioService.salvar(exercicio);
			EducServiceJsfUtil.lancarMensagemInformativa("Exercício salvo com sucesso!");
			this.exercicio = new Exercicio();
			this.pesquisar();
		} catch (EducServiceException e) {
			EducServiceJsfUtil.lancarMensagemDeErro(e.getMessage());
		}
	}

	public void pesquisar() {
		this.exercicios = this.exercicioService
				.pesquisarExerciciosComQuestoesDeProfessor(EducServiceJsfUtil
						.getUsuarioDaSession());
		
		if(this.exercicios == null || this.exercicios.isEmpty()){
			EducServiceJsfUtil.lancarMensagemDeAlerta("Nenhum exercício encontrado!");
		}
	}

	public void editar(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public void removerQuestao(Questao questao) {
		exercicio.getQuestoes().remove(questao);
	}

	public void adicionarQuestao(Questao questao) {
		exercicio.getQuestoes().add(questao);
	}
	
	public void pesquisarQuestoes(){
		this.questoesDePesquisa = this.questaoService.findQuestoesByProfessorEqualsAndNomeLike(EducServiceJsfUtil
				.getUsuarioDaSession(), nomeQuestaoPesquisa);
		
		if(questoesDePesquisa == null || questoesDePesquisa.isEmpty()){
			EducServiceJsfUtil.lancarMensagemDeAlerta("Nenhuma questão encontrada!");
		}
	}

	public List<Questao> getQuestoesPesquisa() {
		return this.questoesDePesquisa;
	}

	public List<Exercicio> getExercicios() {
		return exercicios;
	}

	public void setExercicios(List<Exercicio> exercicios) {
		this.exercicios = exercicios;
	}

	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public String getNomeQuestaoPesquisa() {
		return nomeQuestaoPesquisa;
	}

	public void setNomeQuestaoPesquisa(String nomeQuestaoPesquisa) {
		this.nomeQuestaoPesquisa = nomeQuestaoPesquisa;
	}
}