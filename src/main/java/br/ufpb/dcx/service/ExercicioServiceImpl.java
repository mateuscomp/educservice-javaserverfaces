package br.ufpb.dcx.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.ufpb.dcx.model.Exercicio;
import br.ufpb.dcx.model.Questao;
import br.ufpb.dcx.model.Usuario;

public class ExercicioServiceImpl implements ExercicioService, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Exercicio> pesquisarExerciciosComQuestoesDeProfessor(
			Usuario professor) {

		return Exercicio.findExerciciosWithQuestionsOfTeacher(professor);
	}

	@Override
	public void salvar(Exercicio exercicio) throws EducServiceException {
		validarNaoExistenciaDeDuplicidadeDeQuestao(exercicio.getQuestoes());
		
		if(exercicio.getQuestoes() == null ||exercicio.getQuestoes().isEmpty()){
			throw new EducServiceException("Um exercício deve conter pelo menos uma questão");
		}

		Date dataDeVencimento = exercicio.getDataDeVencimento();
		Calendar calendarComDataDeVencimento = Calendar.getInstance();
		calendarComDataDeVencimento.setTime(dataDeVencimento);
		if (calendarComDataDeVencimento.before(new Date())) {
			throw new EducServiceException(
					"Data de vencimento não pode ser antes da data atual");
		}

		if (exercicio.getId() == null) {
			this.saveExercicio(exercicio);
		} else {
			this.updateExercicio(exercicio);
		}
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