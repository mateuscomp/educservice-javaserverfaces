package br.ufpb.dcx.service;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import br.ufpb.dcx.model.AlternativaDeQuestaoDeMultiplaEscolha;
import br.ufpb.dcx.model.Questao;
import br.ufpb.dcx.model.QuestaoDeMultiplaEscolha;

@RooService(domainTypes = { br.ufpb.dcx.model.QuestaoDeMultiplaEscolha.class })
public interface QuestaoDeMultiplaEscolhaService{

	void salvarQuestaoDeMultiplaEscolha(
			QuestaoDeMultiplaEscolha questaoDeMultiplaEscolha,
			List<AlternativaDeQuestaoDeMultiplaEscolha> alternativasDeQuestaoDeMultiplaEscolhaRemovidas)
			throws QuestaoException;

	QuestaoDeMultiplaEscolha pesquisarPorIdDeQuestao(
			Questao questao);
}
