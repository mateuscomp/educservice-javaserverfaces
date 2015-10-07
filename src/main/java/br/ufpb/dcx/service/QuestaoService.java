package br.ufpb.dcx.service;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import br.ufpb.dcx.model.AlternativaVouF;
import br.ufpb.dcx.model.Questao;
import br.ufpb.dcx.model.Usuario;

@RooService(domainTypes = { br.ufpb.dcx.model.Questao.class })
public interface QuestaoService {

	List<Questao> findQuestoesByProfessorEqualsAndNomeLike(Usuario professor,
			String nome);

	Long findMaxId();

	void salvarQuestao(Questao questao);
}
