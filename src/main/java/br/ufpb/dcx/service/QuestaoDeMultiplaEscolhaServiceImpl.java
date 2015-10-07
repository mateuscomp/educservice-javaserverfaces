package br.ufpb.dcx.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufpb.dcx.model.AlternativaDeQuestaoDeMultiplaEscolha;
import br.ufpb.dcx.model.Questao;
import br.ufpb.dcx.model.QuestaoDeMultiplaEscolha;

public class QuestaoDeMultiplaEscolhaServiceImpl implements
		QuestaoDeMultiplaEscolhaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private AlternativaDeQuestaoDeMultiplaEscolhaService alternativaDeQuestaoDeMultiplaEscolhaService;

	@Autowired
	private QuestaoService questaoService;

	@Override
	public void salvarQuestaoDeMultiplaEscolha(
			QuestaoDeMultiplaEscolha questaoDeMultiplaEscolha,
			List<AlternativaDeQuestaoDeMultiplaEscolha> alternativasDeQuestaoDeMultiplaEscolhaRemovidas)
			throws QuestaoException {

		this.validar(questaoDeMultiplaEscolha);

		for (AlternativaDeQuestaoDeMultiplaEscolha alternativaRemovida : alternativasDeQuestaoDeMultiplaEscolhaRemovidas) {
			if (alternativaRemovida.getId() != null) {
				alternativaDeQuestaoDeMultiplaEscolhaService
						.deleteAlternativaDeQuestaoDeMultiplaEscolha(alternativaRemovida);
			}
		}

		if (questaoDeMultiplaEscolha.getId() == null) {
			Long maiorIDDeQuestao = this.pesquisarMaiorIdDeQuestao();
			if (maiorIDDeQuestao == null) {
				maiorIDDeQuestao = 0L;
			}
			questaoDeMultiplaEscolha.setId(maiorIDDeQuestao + 1);
			saveQuestaoDeMultiplaEscolha(questaoDeMultiplaEscolha);
		} else {
			updateQuestaoDeMultiplaEscolha(questaoDeMultiplaEscolha);
		}

		for (AlternativaDeQuestaoDeMultiplaEscolha alternativa : questaoDeMultiplaEscolha
				.getAlternativas()) {
			if (alternativa.getId() == null) {
				alternativaDeQuestaoDeMultiplaEscolhaService
						.saveAlternativaDeQuestaoDeMultiplaEscolha(alternativa);
			} else {
				alternativaDeQuestaoDeMultiplaEscolhaService
						.updateAlternativaDeQuestaoDeMultiplaEscolha(alternativa);
			}
		}
	}

	private void validar(QuestaoDeMultiplaEscolha questaoDeMultiplaEscolha)
			throws QuestaoException {
		if (questaoDeMultiplaEscolha.getAlternativas().size() < 2) {
			throw new QuestaoException(
					"Questão inválida! Uma questão de múltipla escolha deve ter pelo menos duas alternativas.");
		}

		boolean temRespostaCorreta = false;
		int qtdRespostasCorretas = 0;
		for (AlternativaDeQuestaoDeMultiplaEscolha alternativa : questaoDeMultiplaEscolha
				.getAlternativas()) {
			if (alternativa.getAlternativaCorreta()) {
				temRespostaCorreta = true;
				qtdRespostasCorretas++;
			}
		}

		if (!temRespostaCorreta) {
			throw new QuestaoException(
					"Questão inválida! Uma questão de múltipla escolha deve ter uma resposta correta.");
		}

		if (qtdRespostasCorretas > 1) {
			throw new QuestaoException(
					"Questão inválida! Uma questão de múltipla escolha deve ter apenas uma resposta correta.");
		}
	}

	private Long pesquisarMaiorIdDeQuestao() {
		return questaoService.findMaxId();
	}

	@Override
	public QuestaoDeMultiplaEscolha pesquisarPorIdDeQuestao(Questao questao) {
		return QuestaoDeMultiplaEscolha
				.findQuestaoDeMultiplaEscolhaByIdQuestao(questao.getId());
	}
}
