package br.ufpb.dcx.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufpb.dcx.model.AlternativaVouF;
import br.ufpb.dcx.model.Questao;
import br.ufpb.dcx.model.QuestaoVouF;

public class QuestaoVouFServiceImpl implements QuestaoVouFService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Autowired
	private AlternativaDeQuestaoVouFService alternativaVouFService;

	@Override
	public QuestaoVouF pesquisarPorIdDeQuestao(Questao questao) {
		return QuestaoVouF.findQuestaoVouFByIdDeQuestao(questao.getId());
	}

	@Override
	public void salvarQuestaoVouF(QuestaoVouF questaoVerdadeiroOuFalso,
			List<AlternativaVouF> alternativasRemovidas) throws QuestaoException {
		this.validar(questaoVerdadeiroOuFalso);
		
		if (questaoVerdadeiroOuFalso.getId() == null) {
			saveQuestaoVouF(questaoVerdadeiroOuFalso);
		} else {
			updateQuestaoVouF(questaoVerdadeiroOuFalso);
		}

		for (AlternativaVouF alternativaRemovida : alternativasRemovidas) {
			if (alternativaRemovida.getId() != null) {
				alternativaVouFService
						.deleteAlternativaVouF(alternativaRemovida);
			}
		}
	}

	private void validar(QuestaoVouF questaoVerdadeiroOuFalso) throws QuestaoException {
		int qtdAlternativas = questaoVerdadeiroOuFalso.getAlternativas().size();
		
		if(qtdAlternativas < 1){
			throw new QuestaoException("Questão inválida! Pelo menos uma alternativa deve ser inserida na questão de múltipla escolha.");
		}
	}
}
