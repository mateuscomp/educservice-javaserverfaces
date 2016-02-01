package br.ufpb.dcx.service;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import br.ufpb.dcx.model.AlternativaVouF;
import br.ufpb.dcx.model.Questao;
import br.ufpb.dcx.model.QuestaoVouF;
import br.ufpb.dcx.model.Usuario;

@RooService(domainTypes = { br.ufpb.dcx.model.QuestaoVouF.class })
public interface QuestaoVouFService {

	QuestaoVouF pesquisarPorIdDeQuestao(Questao questao);

	void salvarQuestaoVouF(QuestaoVouF questaoVerdadeiroOuFalso,
			List<AlternativaVouF> alternativasDeQuestaoVouFRemovidas) throws QuestaoException;

	List<QuestaoVouF> findQuestoesByProfessorEqualsAndNomeLike(
			Usuario professor, String nomePesquisa);
}
