package br.ufpb.dcx.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufpb.dcx.model.Exercicio;
import br.ufpb.dcx.model.Questao;
import br.ufpb.dcx.model.Usuario;

public class ExercicioServiceImpl implements ExercicioService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private QuestaoService questaoService;

	@Override
	public List<Exercicio> pesquisarExerciciosComQuestoesDeProfessor(
			Usuario professor) {

		return Exercicio.findExerciciosWithQuestionsOfTeacher(professor);
	}

	@Override
	public void salvar(Exercicio exercicio) throws EducServiceException {
		validarNaoExistenciaDeDuplicidadeDeQuestao(exercicio.getQuestoes());
		if (exercicio.getQuestoes() == null
				|| exercicio.getQuestoes().isEmpty()) {
			throw new EducServiceException(
					"Um exercício deve conter pelo menos uma questão");
		}
		atualizarVerificarExistenciaDasQuestoes(exercicio);
		validarDataDeValidade(exercicio);

		if (exercicio.getId() == null) {
			this.saveExercicio(exercicio);
		} else {
			this.updateExercicio(exercicio);
		}
	}

	private void validarDataDeValidade(Exercicio exercicio)
			throws EducServiceException {
		Calendar calendarComDataDeVencimento = Calendar.getInstance();
		int anoAtual = calendarComDataDeVencimento.get(Calendar.YEAR);
		int mesAtual = calendarComDataDeVencimento.get(Calendar.MONTH);
		int dataAtual = calendarComDataDeVencimento.get(Calendar.DATE);

		calendarComDataDeVencimento.setTime(exercicio.getDataDeVencimento());
		int anoDoExercicio = calendarComDataDeVencimento.get(Calendar.YEAR);
		int mesDoExercicio= calendarComDataDeVencimento.get(Calendar.MONTH);
		int dataDoExercicio = calendarComDataDeVencimento.get(Calendar.DATE);

		if (anoAtual == anoDoExercicio) {
			if (mesAtual == mesDoExercicio){
				if (dataAtual > dataDoExercicio) {
					throw new EducServiceException(
							"Data de vencimento não pode ser antes da data atual");
				}
			} else if (mesAtual > mesDoExercicio){
				throw new EducServiceException(
						"Data de vencimento não pode ser antes da data atual");
			}
		} else if (anoAtual > anoDoExercicio){
			throw new EducServiceException(
					"Data de vencimento não pode ser antes da data atual");
		}
	}

	private void atualizarVerificarExistenciaDasQuestoes(Exercicio exercicio) {
		List<Questao> questoesDoExercicio = exercicio.getQuestoes();
		List<Questao> questoesPesquisadas = new LinkedList<Questao>();

		for (Questao questao : questoesDoExercicio) {
			Questao questaoPesquisada = questaoService.findQuestao(questao
					.getId());
			questoesPesquisadas.add(questaoPesquisada);
		}
		exercicio.setQuestoes(questoesPesquisadas);
	}

	private void validarNaoExistenciaDeDuplicidadeDeQuestao(
			List<Questao> questoes) throws EducServiceException {

		for (Questao q1 : questoes) {
			for (Questao q2 : questoes) {
				int qtdMesmaQuestaoEncontrada = 0;

				if (q1.getId().equals(q2.getId())) {
					if (qtdMesmaQuestaoEncontrada >= 1) {
						throw new EducServiceException(
								"Exercício possui questões duplicadas!");
					} else {
						qtdMesmaQuestaoEncontrada++;
					}
				}
			}
		}
	}
}